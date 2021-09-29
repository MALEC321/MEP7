package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Action;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurFactory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

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

  public String createDinosaur(DinosaurCreationDto dto) {
    Dinosaur dinosaur = dinosaurFactory.create(dto.name, dto.weight, dto.gender, dto.species);
    addDinoToActionWaitingList(dinosaur);
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

  private void addDinoToActionWaitingList(Dinosaur dinosaur) {
      Action addDinos = actionFactory.create(dinosaur, Command.ADD_DINO, dinosaurRepository);
      actionRepository.save(addDinos);
  }
}
