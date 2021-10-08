package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.actions.Command;
import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.exceptions.types.InvalidFatherException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

import java.util.Objects;

public class BabyUseCase {

    private final DinosaurRepository dinosaurRepository;
    private final BabyAssembler babyAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;
    private final DinosaurFactory dinosaurFactory;

    public BabyUseCase(DinosaurRepository dinosaurRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory, DinosaurFactory dinosaurFactory) {
        this.dinosaurRepository = dinosaurRepository;
        this.babyAssembler = babyAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
        this.dinosaurFactory = dinosaurFactory;
    }

    public void createBebe(BabyCreationDto dto) {
        DinosaurBaby dinosaurBaby = dinosaurFactory.create(dto.name, dto.fatherName, dto.motherName, dto.gender, dto.species);
        addDinoToActionWaitingList(dinosaurBaby);
    }

    public ExternalApiCreationDto getParentsSpecies(String fatherName, String motherName) {
        String fatherSpecies = null;
        String motherSpecies = null;

        if (dinosaurRepository.findByName(fatherName) == null) {
            throw new NotExistentNameException();
        }

        if (dinosaurRepository.findByName(motherName) == null) {
            throw new NotExistentNameException();
        }

        if (!Objects.equals(dinosaurRepository.findByName(fatherName).getGender(), "m")) {
            throw new InvalidFatherException();
        }

        if (!Objects.equals(dinosaurRepository.findByName(motherName).getGender(), "f")) {
            throw new InvalidMotherException();
        }

        Dinosaur dinosaurFather = dinosaurRepository.findByName(fatherName);
        fatherSpecies = dinosaurFather.getSpecies();

        Dinosaur dinosaurMother = dinosaurRepository.findByName(motherName);
        motherSpecies = dinosaurMother.getSpecies();

        return babyAssembler.toExternalDto(fatherSpecies, motherSpecies);
    }

    private void addDinoToActionWaitingList(DinosaurBaby dinosaur) {
        Action addDinos = actionFactory.create(dinosaur, Command.ADD_DINO, dinosaurRepository);
        actionRepository.save(addDinos);
    }
}
