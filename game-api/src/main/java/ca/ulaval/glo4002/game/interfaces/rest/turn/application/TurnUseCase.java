package ca.ulaval.glo4002.game.interfaces.rest.turn.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Action;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Burger;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Salad;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Water;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;

import java.util.List;
import java.util.UUID;

public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final ResourceRepository resourceRepository;
    private final TurnAssembler turnAssembler;
    private final ActionRepository actionRepository;
    private final DinosaurRepository dinosaurRepository;

    public TurnUseCase(
            TurnFactory turnFactory,
            TurnRepository turnRepository,
            ResourceRepository resourceRepository,
            DinosaurRepository dinosaurRepository,
            TurnAssembler turnAssembler,
            ActionRepository actionRepository) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.resourceRepository = resourceRepository;
        this.dinosaurRepository = dinosaurRepository;
        this.turnAssembler = turnAssembler;
        this.actionRepository = actionRepository;
    }

    public void createTurn() {
        //Todo This not gonna work with the turn because we need to find a way to add the cookIt as well
        List<Action> actions = actionRepository.getWaitingActions();

        Turn turn = turnFactory.create(actions);
        turnRepository.save(turn);

        actionRepository.execute();
        postAction();

    }

    private void cookIt() {
        resourceRepository.add(new Burger(100));
        resourceRepository.add(new Salad(250));
        resourceRepository.add(new Water(10000));
    }

    public void postAction() { //Todo rajouter les post action ici
        cookIt();

        resourceRepository.decreaseExpirationDate();

        feedDinosaurs();

    }

    public TurnDto getFromId(UUID id) {
        Turn turn = turnRepository.findById(id);

        return turnAssembler.toDto(turn);
    }

    public void feedDinosaurs() {
        feedDinosaursByDietType(dinosaurRepository.getSortedDinosaursByStrengthThenName());
    }

    //TODO: Test uniter cette méthode
    private void feedDinosaursByDietType(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        for(Dinosaur dinosaur: sortedDinosaursByStrengthThenName) {
            if (dinosaur.getDiet().equals(DietType.HERBIVORE)) {
                if(!resourceRepository.consume(new Salad(0), dinosaur.feedFood())){
                    dinosaurRepository.remove(dinosaur);
                }
            } else {
                if(!resourceRepository.consume(new Burger(0), dinosaur.feedFood())) {
                    dinosaurRepository.remove(dinosaur);
                }
            }

            if(!resourceRepository.consume(new Water(0), dinosaur.feedWater())) {
                dinosaurRepository.remove(dinosaur);
            }
        }
    }

    public void reset() {
        turnRepository.reset();
        resourceRepository.reset();
        dinosaurRepository.reset();
    }
}
