package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.OrderForm;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

import java.util.List;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class TurnService {
    private final TurnFactory turnFactory;
    private final GameRepository gameRepository;
    private final PantryRepository pantryRepository;
    private final ActionRepository actionRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;

    public TurnService(
        TurnFactory turnFactory,
        GameRepository gameRepository,
        PantryRepository pantryRepository,
        HerdRepository herdRepository,
        ActionRepository actionRepository,
        ResourcesDistributor resourcesDistributor,
        ResourcesFactory resourcesFactory) {
        this.turnFactory = turnFactory;
        this.gameRepository = gameRepository;
        this.pantryRepository = pantryRepository;
        this.herdRepository = herdRepository;
        this.actionRepository = actionRepository;
        this.resourcesDistributor = resourcesDistributor;
        this.resourcesFactory = resourcesFactory;
    }

    public void executeTurn() {
        List<Action> actions = actionRepository.getActionList();
        cookIt();

        actionRepository.executeActions();
        postAction();

        Game game = gameRepository.findGame();
        Turn turn = turnFactory.create(game.nextTurnNumber(), actions);
        game.addTurn(turn);
        gameRepository.save(game);
    }

    protected void cookIt() {
        Pantry pantry = pantryRepository.findPantry();
        pantry.addResources(resourcesFactory.create(BURGER, 100));
        pantry.addResources(resourcesFactory.create(SALAD, 250));
        pantry.addResources(resourcesFactory.create(WATER, 10000));
    }

    // TODO: Should be end turn Actions
    private void postAction() {
        pantryRepository.findPantry().decreaseExpirationDate();
        feedDinosaurs();
        removeOrphanedBabyDinosaurs();
    }

    protected void feedDinosaurs() {
        Pantry pantry = pantryRepository.findPantry();
        Herd herd = herdRepository.findHerd();
        int burgersQuantity = pantry.findFreshResources().getResourceQuantity(BURGER);
        int saladsQuantity = pantry.findFreshResources().getResourceQuantity(SALAD);
        int waterQuantity = pantry.findFreshResources().getResourceQuantity(WATER);
        OrderForm orderForm = new OrderForm(burgersQuantity, saladsQuantity, waterQuantity);
        orderForm = herd.feedDinosaurs(orderForm);
        herdRepository.save(herd);
        pantry.removeQuantity(orderForm);
        pantryRepository.save(pantry);
    }

    protected void removeOrphanedBabyDinosaurs() {
        Herd herd = herdRepository.findHerd();
        herd.removeOrphanedBabyDinosaurs();
    }

    public TurnNumber getTurnNumber() {
        Game game = gameRepository.findGame();
        return game.currentTurnNumber();
    }

    public void resetGame() {
        gameRepository.deleteAll();
        pantryRepository.deleteAll();
        herdRepository.deleteAll();
        actionRepository.deleteAll();
    }
}
