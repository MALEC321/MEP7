package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurFactory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import java.util.List;

public class DinosaurUseCase {

  private final DinosaurFactory dinosaurFactory;
  private final DinosaurRepository dinosaurRepository;
  private final DinosaurAssembler dinosaurAssembler;

  public DinosaurUseCase(
      DinosaurFactory dinosaurFactory,
      DinosaurRepository dinosaurRepository,
      DinosaurAssembler dinosaurAssembler) {
    this.dinosaurFactory = dinosaurFactory;
    this.dinosaurRepository = dinosaurRepository;
    this.dinosaurAssembler = dinosaurAssembler;
  }

  public String createDinosaur(DinosaurCreationDto dto) {
    Dinosaur dinosaur = dinosaurFactory.create(dto.name, dto.weight, dto.gender, dto.species);
    dinosaurRepository.save(dinosaur);
    return dinosaur.name;
  }

  public List<DinosaurDto> getAllDinosaurs() {
    List<Dinosaur> dinosaurs = dinosaurRepository.findAll();
    return dinosaurAssembler.toDtos(dinosaurs);
  }

  public DinosaurDto getDinosaur(String name) {
    Dinosaur dinosaur = dinosaurRepository.findByName(name);
    return dinosaurAssembler.toDto(dinosaur);
  }

  private double calculateWaterNeed(int weight) {
    return Math.round(weight * 0.6) + 2 * 3;
  }

  private double calculateFoodNeed(int weight, String specy, boolean isNewlyCreated) {
    double foodNeed = (weight * getPonderationByDietType(specy)) / 200;
    if (isNewlyCreated) {
      foodNeed = Math.ceil(foodNeed * 2);
    }
    return foodNeed;
  }

  private double getPonderationByDietType(String specy) {
    return SpeciesDiet.valueOf(specy).equals(DietType.HERBIVORE) ? 0.5 : 0.2;
  }
}
