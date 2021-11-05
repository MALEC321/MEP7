package ca.ulaval.glo4002.game.application.turn;

import java.util.List;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Burger;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.Salad;
import ca.ulaval.glo4002.game.domain.resources.Water;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;

public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final ResourceRepository resourceRepository;
    private final ActionRepository actionRepository;
    private final HerdRepository herdRepository;
    private final ZooManager zooManager;

    public TurnUseCase(
        TurnFactory turnFactory,
        TurnRepository turnRepository,
        ResourceRepository resourceRepository,
        HerdRepository herdRepository,
        ActionRepository actionRepository,
        ZooManager zooManager) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.resourceRepository = resourceRepository;
        this.herdRepository = herdRepository;
        this.actionRepository = actionRepository;
        this.zooManager = zooManager;
    }

    public void createTurn() {
        List<Action> actions = actionRepository.getWaitingActions();
        cookIt();

        actionRepository.execute();
        postAction();

        Turn turn = turnFactory.create(actions);
        turnRepository.save(turn);
    }

    protected void cookIt() {
        resourceRepository.add(new Burger(100));
        resourceRepository.add(new Salad(250));
        resourceRepository.add(new Water(10000));
    }

    private void postAction() {
        resourceRepository.decreaseExpirationDate();
        feedDinosaurs();
        removeBabyDinosaurs();
    }

    protected void feedDinosaurs() {
        zooManager.feedDinosaurs(resourceRepository.getPantry(), herdRepository.findHerd());
    }

    protected void removeBabyDinosaurs() {
        Herd herd = herdRepository.findHerd();
        removeBabyDinosaur(herd.getSortedDinosaursByStrengthThenName());
    }

    private void removeBabyDinosaur(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        Herd herd = herdRepository.findHerd();
        for (Dinosaur dinosaur : sortedDinosaursByStrengthThenName) {
            if (dinosaur instanceof DinosaurBaby && herd.areBothParentsDead(dinosaur)) {
                herd.remove(dinosaur);
            }
        }
    }

    public void reset() {
        turnRepository.reset();
        resourceRepository.reset();
        herdRepository.reset();
        actionRepository.reset();
    }
}
