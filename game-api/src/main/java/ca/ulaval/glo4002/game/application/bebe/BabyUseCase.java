package ca.ulaval.glo4002.game.application.bebe;

import ca.ulaval.glo4002.game.application.bebe.dtos.BebeAssembler;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeCreationDto;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.*;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

public class BabyUseCase {

    private final BabyFactory bebeFactory;
    private final DinosaurRepository dinosaurRepository;
    private final BebeAssembler bebeAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public BabyUseCase(BabyFactory bebeFactory, DinosaurRepository dinosaurRepository, BebeAssembler bebeAssembler, ActionRepository actionRepository, ActionFactory actionFactory) {
        this.bebeFactory = bebeFactory;
        this.dinosaurRepository = dinosaurRepository;
        this.bebeAssembler = bebeAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createBebe(BebeCreationDto dto) {
        DinosaurBaby bebe = bebeFactory.create(dto.name, dto.fatherName, dto.motherName);
    }

    public ExternalApiCreationDto getParentsSpecies(String fatherName, String motherName) {
        String fatherSpecies = null;
        String motherSpecies = null;
        if (dinosaurRepository.findByName(fatherName) == null) {
            throw new NotExistentNameException();
        } else {
            Dinosaur dinosaurFather = dinosaurRepository.findByName(fatherName);
            fatherSpecies = dinosaurFather.getSpecies();
        }
        if (dinosaurRepository.findByName(motherName) == null) {
            throw new NotExistentNameException();
        } else {
            Dinosaur dinosaurMother = dinosaurRepository.findByName(motherName);
            motherSpecies = dinosaurMother.getSpecies();
        }
        return bebeAssembler.toDto(fatherSpecies, motherSpecies);
    }
}
