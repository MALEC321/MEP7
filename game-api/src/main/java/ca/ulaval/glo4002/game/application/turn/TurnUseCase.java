package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
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
    private final DinosaurRepository dinosaurRepository;
    private final ZooManager zooManager;
    private final ResourceFactory resourceFactory;


    public TurnUseCase(
        TurnFactory turnFactory,
        TurnRepository turnRepository,
        ResourceRepository resourceRepository,
        DinosaurRepository dinosaurRepository,
        ActionRepository actionRepository,
        ZooManager zooManager,
        ResourceFactory resourceFactory) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.resourceRepository = resourceRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.actionRepository = actionRepository;
        this.zooManager = zooManager;
        this.resourceFactory = resourceFactory;
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
        resourceRepository.add(resourceFactory.create(BURGER, 100));
        resourceRepository.add(resourceFactory.create(SALAD, 250));
        resourceRepository.add(resourceFactory.create(WATER, 1000));
    }

    private void postAction() {
        resourceRepository.decreaseExpirationDate();
        feedDinosaurs();
        removeBabyDinosaurs();
    }

    protected void feedDinosaurs() {
        feedDinosaursByDietType(dinosaurRepository.getSortedDinosaursByStrengthThenName());
    }

    protected void removeBabyDinosaurs() {
        removeBabyDinosaur(dinosaurRepository.getSortedDinosaursByStrengthThenName());
    }

    protected void feedDinosaursByDietType(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        for (Dinosaur dinosaur : sortedDinosaursByStrengthThenName) {
            zooManager.feedDinosaur(resourceRepository.getPantry(), dinosaur);
            if (dinosaur.isStarving()) {
                dinosaurRepository.remove(dinosaur);
            }
        }
    }

    private void removeBabyDinosaur(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        for (Dinosaur dinosaur : sortedDinosaursByStrengthThenName) {
            if (dinosaur instanceof DinosaurBaby && dinosaurRepository.areBothParentsDead(dinosaur)) {
                dinosaurRepository.remove(dinosaur);
            }
        }
    }

    public void reset() {
        turnRepository.reset();
        resourceRepository.reset();
        dinosaurRepository.reset();
        actionRepository.reset();
    }
}
