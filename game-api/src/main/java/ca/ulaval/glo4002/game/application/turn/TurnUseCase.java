package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;

import java.util.List;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final ResourceRepository resourceRepository;
    private final ActionRepository actionRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;

    public TurnUseCase(
        TurnFactory turnFactory,
        TurnRepository turnRepository,
        ResourceRepository resourceRepository,
        HerdRepository herdRepository,
        ActionRepository actionRepository,
        ResourcesDistributor resourcesDistributor,
        ResourcesFactory resourcesFactory) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.resourceRepository = resourceRepository;
        this.herdRepository = herdRepository;
        this.actionRepository = actionRepository;
        this.resourcesDistributor = resourcesDistributor;
        this.resourcesFactory = resourcesFactory;
    }

    public void executeTurn() {
        List<Action> actions = actionRepository.getWaitingActions();
        cookIt();

        actionRepository.execute();
        postAction();

        Turn turn = turnFactory.create(actions);
        turnRepository.save(turn);
    }

    protected void cookIt() {
        resourceRepository.add(resourcesFactory.create(BURGER, 100));
        resourceRepository.add(resourcesFactory.create(SALAD, 250));
        resourceRepository.add(resourcesFactory.create(WATER, 10000));
    }

    // TODO: Should be end turn Actions
    private void postAction() {
        resourceRepository.decreaseExpirationDate();
        feedDinosaurs();
        removeBabyDinosaurs();
    }

    protected void feedDinosaurs() {
        resourcesDistributor.feedDinosaurs(resourceRepository.getPantry(), herdRepository.findHerd());
    }

    protected void removeBabyDinosaurs() {
        Herd herd = herdRepository.findHerd();
        herd.removeOrphanedBabyDinosaurs();
    }

    public void reset() {
        turnRepository.reset();
        resourceRepository.reset();
        herdRepository.reset();
        actionRepository.reset();
    }
}
