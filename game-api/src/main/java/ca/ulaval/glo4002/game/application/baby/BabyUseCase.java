package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

import java.util.Objects;

public class BabyUseCase {

    private final BabyFactory bebeFactory;
    private final DinosaurRepository dinosaurRepository;
    private final BabyAssembler babyAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public BabyUseCase(BabyFactory bebeFactory, DinosaurRepository dinosaurRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory) {
        this.bebeFactory = bebeFactory;
        this.dinosaurRepository = dinosaurRepository;
        this.babyAssembler = babyAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createBebe(BabyCreationDto dto) {
        bebeFactory.create(dto.name, dto.fatherName, dto.motherName);
    }

    public ExternalApiCreationDto getParentsSpecies(String fatherName, String motherName) {
        String fatherSpecies = null;
        String motherSpecies = null;
        if (dinosaurRepository.findByName(fatherName) == null) {
            throw new NotExistentNameException();
        } else if(Objects.equals(dinosaurRepository.findByName(fatherName).getGender(), "m")) {
            Dinosaur dinosaurFather = dinosaurRepository.findByName(fatherName);
            fatherSpecies = dinosaurFather.getSpecies();
        }
        if (dinosaurRepository.findByName(motherName) == null) {
            throw new NotExistentNameException();
        } else if(Objects.equals(dinosaurRepository.findByName(motherName).getGender(), "f")) {
            Dinosaur dinosaurMother = dinosaurRepository.findByName(motherName);
            motherSpecies = dinosaurMother.getSpecies();
        }
        return babyAssembler.toExternalDto(fatherSpecies, motherSpecies);
    }
}
