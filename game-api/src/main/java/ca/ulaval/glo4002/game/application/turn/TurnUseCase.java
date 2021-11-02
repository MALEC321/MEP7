package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.Burger;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.Salad;
import ca.ulaval.glo4002.game.domain.resources.Water;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;

import java.util.List;

public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final ResourceRepository resourceRepository;
    private final ActionRepository actionRepository;
    private final DinosaurRepository dinosaurRepository;
    private final ZooManager zooManager;

    public TurnUseCase(
        TurnFactory turnFactory,
        TurnRepository turnRepository,
        ResourceRepository resourceRepository,
        DinosaurRepository dinosaurRepository,
        ActionRepository actionRepository,
        ZooManager zooManager) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.resourceRepository = resourceRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.actionRepository = actionRepository;
        this.zooManager = zooManager;
    }

    public TurnDto createTurn() {
        List<Action> actions = actionRepository.getWaitingActions();
        cookIt();

        actionRepository.execute();
        postAction();

        Turn turn = turnFactory.create(actions);
        turnRepository.save(turn);

        return new TurnDto(Turn.number);
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
        feedDinosaursByDietType(dinosaurRepository.getSortedDinosaursByStrengthThenName());
    }

    protected void removeBabyDinosaurs() {
        removeBabyDinosaur(dinosaurRepository.getSortedDinosaursByStrengthThenName());
    }
    protected void feedDinosaursByDietType(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        boolean isStarving;

        for (Dinosaur dinosaur : sortedDinosaursByStrengthThenName) {
            isStarving = zooManager.feedThenCheckIfStarving(resourceRepository.getPantry(), dinosaur);
            if (isStarving) {
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
