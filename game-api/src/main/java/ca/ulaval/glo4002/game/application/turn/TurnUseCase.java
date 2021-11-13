package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.domain.turn.Turns;

import java.util.List;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class TurnUseCase {
    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final PantryRepository pantryRepository;
    private final ActionRepository actionRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;

    public TurnUseCase(
        TurnFactory turnFactory,
        TurnRepository turnRepository,
        PantryRepository pantryRepository,
        HerdRepository herdRepository,
        ActionRepository actionRepository,
        ResourcesDistributor resourcesDistributor,
        ResourcesFactory resourcesFactory) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
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

        Turns turns = turnRepository.findTurns();
        Turn turn = turnFactory.create(turns.nextTurnNumber(), actions);
        turns.addTurn(turn);
        turnRepository.save(turns);
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
        resourcesDistributor.feedDinosaurs(pantryRepository.findPantry(), herdRepository.findHerd());
    }

    protected void removeOrphanedBabyDinosaurs() {
        Herd herd = herdRepository.findHerd();
        herd.removeOrphanedBabyDinosaurs();
    }

    public int getTurnNumber() {
        Turns turns = turnRepository.findTurns();
        return turns.numberOfTurns();
    }

    public void reset() {
        turnRepository.reset();
        pantryRepository.reset();
        herdRepository.reset();
        actionRepository.reset();
    }
}
