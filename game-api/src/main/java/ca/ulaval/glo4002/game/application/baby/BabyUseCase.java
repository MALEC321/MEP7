package ca.ulaval.glo4002.game.application.baby;

import java.util.Objects;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.exceptions.types.InvalidFatherException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

public class BabyUseCase {

    private final HerdRepository herdRepository;
    private final BabyAssembler babyAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;
    private final DinosaurFactory dinosaurFactory;

    public BabyUseCase(HerdRepository herdRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory,
                       DinosaurFactory dinosaurFactory) {
        this.herdRepository = herdRepository;
        this.babyAssembler = babyAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
        this.dinosaurFactory = dinosaurFactory;
    }

    public void createBebe(BabyCreationDto dto) {
        Dinosaur baby = dinosaurFactory.create(dto.name, dto.fatherName, dto.motherName, dto.gender, dto.species);
        actionRepository.save(actionFactory.create(baby, herdRepository));
    }

    public ExternalApiCreationDto getParentsSpecies(String fatherName, String motherName) {
        Herd herd = herdRepository.findHerd();

        String fatherSpecies;
        String motherSpecies;

        if (herd.findByName(fatherName) == null) {
            throw new NotExistentNameException();
        }

        if (herd.findByName(motherName) == null) {
            throw new NotExistentNameException();
        }

        if (!Objects.equals(herd.findByName(fatherName).getGender(), "m")) {
            throw new InvalidFatherException();
        }

        if (!Objects.equals(herd.findByName(motherName).getGender(), "f")) {
            throw new InvalidMotherException();
        }

        Dinosaur dinosaurFather = herd.findByName(fatherName);
        fatherSpecies = dinosaurFather.getSpecies();

        Dinosaur dinosaurMother = herd.findByName(motherName);
        motherSpecies = dinosaurMother.getSpecies();

        return babyAssembler.toExternalDto(fatherSpecies, motherSpecies);
    }
}
