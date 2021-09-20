package ca.ulaval.glo4002.game.interfaces.rest.turn.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Actions;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Burger;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Salad;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.Water;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    setupActions(actionRepository);

    List<Actions> actions = actionRepository.getWaitingActions();
    Turn turn = turnFactory.create(actions);
    actionRepository.execute();
    turnRepository.save(turn);
    feedDinosaurs();
  }

  public void setupActions(ActionRepository actionRepository) {
    Actions addWater = new ActionFactory().create(new Water(10), Command.ADD, resourceRepository);
    Actions addSalad = new ActionFactory().create(new Salad(250), Command.ADD, resourceRepository);
    Actions addBurger =
        new ActionFactory().create(new Burger(100), Command.ADD, resourceRepository);
    actionRepository.save(addWater);
    actionRepository.save(addSalad);
    actionRepository.save(addBurger);
  }

  public TurnDto getFromId(UUID id) {
    Turn turn = turnRepository.findById(id);

    return turnAssembler.toDto(turn);
  }

  public void feedDinosaurs() {
    List<Dinosaur> sortedDinosByForce = getSortedDinosaursByStrength();

    feedDinausaursByDietType(sortedDinosByForce);
  }

  private void feedDinausaursByDietType(List<Dinosaur> sortedDinosByForce) {
    sortedDinosByForce.forEach(
        dinosaur -> {
          double fooddNeed = 0.0;
          double waterNeed = calculateWaterNeed(dinosaur.getWeight());

          if (dinosaur.getDiet().equals(DietType.HERBIVORE)) {
            fooddNeed = calculateFoodNeed(dinosaur);
            ResourceElements resourceElements = resourceRepository.findAll().element();
            // resourceElements. consommer la saladNeed
              // TODO: getter sur les salads ou burgers ou eau
          } else {
            fooddNeed = calculateFoodNeed(dinosaur);
            // resourceElements. consommer la saladNeed
          }
        });
  }

  private List<Dinosaur> getSortedDinosaursByStrength() {
    return dinosaurRepository.findAll().stream()
        .sorted(Comparator.comparingDouble(Dinosaur::getForce))
        .sorted(Comparator.comparing(Dinosaur::getName))
        .collect(Collectors.toList());
  }

  private double calculateWaterNeed(int weight) {
    return Math.round(weight * 0.6) + 2 * 3;
  }

  private double calculateFoodNeed(Dinosaur dinosaur) {
    double foodNeed =
        (dinosaur.getWeight() * getPonderationByDietType(dinosaur.getSpecies())) / 200;
    boolean isNewlyCreated = true; // Comment savoir que c'est un nouveau dino ?

    if (isNewlyCreated) {
      foodNeed = Math.ceil(foodNeed * 2);
    }

    return foodNeed;
  }

  private double getPonderationByDietType(String specy) {
    return SpeciesDiet.valueOf(specy).equals(DietType.HERBIVORE) ? 0.5 : 0.2;
  }
}
