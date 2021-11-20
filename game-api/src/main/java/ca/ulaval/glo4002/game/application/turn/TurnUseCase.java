package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class TurnUseCase {
    private final TurnFactory turnFactory;
    private final GameRepository gameRepository;
    private final PantryRepository pantryRepository;
    private final ActionRepository actionRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;
    private final ActionFactory actionFactory;

    public TurnUseCase(
        TurnFactory turnFactory,
        GameRepository gameRepository,
        PantryRepository pantryRepository,
        HerdRepository herdRepository,
        ActionRepository actionRepository,
        ResourcesDistributor resourcesDistributor,
        ResourcesFactory resourcesFactory,
        ActionFactory actionFactory) {
        this.turnFactory = turnFactory;
        this.gameRepository = gameRepository;
        this.pantryRepository = pantryRepository;
        this.herdRepository = herdRepository;
        this.actionRepository = actionRepository;
        this.resourcesDistributor = resourcesDistributor;
        this.resourcesFactory = resourcesFactory;
        this.actionFactory = actionFactory;
    }

    public void playTurn() {
        Game game = gameRepository.findGame();

        addCookItConsequences(game);

        Turn currentTurn = game.currentTurn();

        currentTurn.executeActions();
        turnConsequences();

        endTurn(game);
        gameRepository.save(game);
    }

    protected void addCookItConsequences(Game game) {
        Pantry pantry = pantryRepository.findPantry();
        Action addCookIt = actionFactory.create(pantry, resourcesFactory);
        Turn currentTurn = game.currentTurn();
        currentTurn.addAction(addCookIt);
    }

    // TODO: Should be end turn Actions
    private void turnConsequences() {
        pantryRepository.findPantry().decreaseExpirationDate();
        feedDinosaurs();
        removeOrphanedBabyDinosaurs();
    }

    private void endTurn(Game game) {
        Turn turn = turnFactory.create(game.nextTurnNumber());
        game.addTurn(turn);
    }

    protected void feedDinosaurs() {
        resourcesDistributor.feedDinosaurs(pantryRepository.findPantry(), herdRepository.findHerd());
    }

    protected void removeOrphanedBabyDinosaurs() {
        Herd herd = herdRepository.findHerd();
        herd.removeOrphanedBabyDinosaurs();
    }

    public TurnNumber getLastPlayedTurnNumber() {
        Game game = gameRepository.findGame();
        return game.lastPlayedTurnNumber();
    }

    public void resetGame() {
        gameRepository.deleteAll();
        pantryRepository.deleteAll();
        herdRepository.deleteAll();
        actionRepository.deleteAll();
    }
}
