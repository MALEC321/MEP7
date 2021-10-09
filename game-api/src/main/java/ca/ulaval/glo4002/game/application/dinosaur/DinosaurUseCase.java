package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

import java.util.List;

public class DinosaurUseCase {
  
  private final DinosaurFactory dinosaurFactory;
  private final DinosaurRepository dinosaurRepository;
  private final DinosaurAssembler dinosaurAssembler;
  private final ActionRepository actionRepository;
  private final ActionFactory actionFactory;

  public DinosaurUseCase(
          DinosaurFactory dinosaurFactory,
          DinosaurRepository dinosaurRepository,
          DinosaurAssembler dinosaurAssembler, ActionRepository actionRepository, ActionFactory actionFactory) {
    this.dinosaurFactory = dinosaurFactory;
    this.dinosaurRepository = dinosaurRepository;
    this.dinosaurAssembler = dinosaurAssembler;
    this.actionRepository = actionRepository;
    this.actionFactory = actionFactory;
  }

  public void createDinosaur(DinosaurCreationDto dto) {
    Dinosaur dinosaur = dinosaurFactory.create(dto.name, dto.weight, dto.gender, dto.species);
    actionRepository.save(actionFactory.create(dinosaur, dinosaurRepository));
  }

  public List<DinosaurDto> getAllDinosaurs() {
    List<Dinosaur> dinosaurs = dinosaurRepository.findAll();
    return dinosaurAssembler.toDtos(dinosaurs);
  }

  public DinosaurDto getDinosaur(String name) throws NotExistentNameException {
    if (dinosaurRepository.findByName(name) == null) {
      throw new NotExistentNameException();
    }
    Dinosaur dinosaur = dinosaurRepository.findByName(name);
    return dinosaurAssembler.toDto(dinosaur);
  }
}
