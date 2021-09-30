package ca.ulaval.glo4002.game.interfaces.rest.turn.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Action;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.*;
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
        preAction(actionRepository);
        List<Action> actions = actionRepository.getWaitingActions();
        Turn turn = turnFactory.create(actions);
        actionRepository.execute();
        turnRepository.save(turn);
        postAction();
    }

    public void preAction(ActionRepository actionRepository) {
        Action addWater = new ActionFactory().create(new Water(10000), Command.ADD, resourceRepository);
        Action addSalad = new ActionFactory().create(new Salad(250), Command.ADD, resourceRepository);
        Action addBurger = new ActionFactory().create(new Burger(100), Command.ADD, resourceRepository);
        actionRepository.save(addWater);
        actionRepository.save(addSalad);
        actionRepository.save(addBurger);
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
        feedDinosaursByDietType(dinosaurRepository.getSortedDinosaursByStrength());
    }

    //TODO: Test uniter cette méthode
    private void feedDinosaursByDietType(List<Dinosaur> sortedDinosaursByForce) {
        for(Dinosaur dinosaur: sortedDinosaursByForce) {
            if (dinosaur.getDiet().equals(DietType.HERBIVORE)) {
                if(!resourceRepository.consume(new Salad(0), dinosaur.getFoodNeed())) dinosaurRepository.remove(dinosaur);
            } else {
                if(!resourceRepository.consume(new Burger(0), dinosaur.getFoodNeed())) dinosaurRepository.remove(dinosaur);
            }
            if(!resourceRepository.consume(new Water(0), dinosaur.getWaterNeed())) dinosaurRepository.remove(dinosaur);

            if (dinosaur.isNewlyAdded()) {
                dinosaur.setNewlyAdded(false);
            }
        }
    }

}
