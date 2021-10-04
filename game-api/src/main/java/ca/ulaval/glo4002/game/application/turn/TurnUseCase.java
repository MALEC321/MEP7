package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnAssembler;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.resources.Burger;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.Salad;
import ca.ulaval.glo4002.game.domain.resources.Water;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDto;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.repositories.turn.TurnRepository;

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
        cookIt();

        actionRepository.execute();
        postAction();

        Turn turn = turnFactory.create(actions); //+1
        turnRepository.save(turn);
    }

    private void cookIt() {
        resourceRepository.add(new Burger(100));
        resourceRepository.add(new Salad(250));
        resourceRepository.add(new Water(10000));
    }

    public void postAction() { //Todo rajouter les post action ici
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
            boolean dead = false;
            if (dinosaur.getDiet().equals(DietType.HERBIVORE)) {
                if(!resourceRepository.eatSalad(dinosaur.feedFood())){
                    dead = true;
                }
            } else {
                if(!resourceRepository.eatBurger(dinosaur.feedFood())) {
                    dead = true;
                }
            }

            if(!resourceRepository.drinkWater(dinosaur.feedWater())) {
                dead = true;
            }

            if (dead) dinosaurRepository.remove(dinosaur);
        }
    }

    public void reset() {
        turnRepository.reset();
        resourceRepository.reset();
        dinosaurRepository.reset();
        actionRepository.reset();
    }
}
