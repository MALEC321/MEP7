package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

import java.util.Objects;
import java.util.Optional;

public class BabyUseCase {
    private final HerdRepository herdRepository;
    private final BabyAssembler babyAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;
    private final DinosaurFactory dinosaurFactory;
    private final Breedable breedable;

    public BabyUseCase(HerdRepository herdRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory,
                       DinosaurFactory dinosaurFactory, Breedable breedable) {
        this.herdRepository = herdRepository;
        this.babyAssembler = babyAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
        this.dinosaurFactory = dinosaurFactory;
        this.breedable = breedable;
    }

    public void createBaby(BabyCreationDto dto) {
        Optional<ResponseBreed> babyDto = tryToGiveBirthToBaby(dto);
        saveBabyInformationIfBabyAlived(dto, babyDto);
    }

    private void saveBabyInformationIfBabyAlived(BabyCreationDto dto, Optional<ResponseBreed> babyDto) {
        if (babyDto.isPresent()) {
            Dinosaur father = herdRepository.findHerd().findDinosaurByName(dto.getFatherName());
            Dinosaur mother = herdRepository.findHerd().findDinosaurByName(dto.getMotherName());
            Dinosaur baby = dinosaurFactory.createBabyDinosaur(dto.getName(), mother, father,
                    babyDto.get().getGender(), babyDto.get().getOffspring());
            actionRepository.save(actionFactory.create(baby, herdRepository.findHerd()));
        }
    }

    private Optional<ResponseBreed> tryToGiveBirthToBaby(BabyCreationDto dto) {
        RequestBreed requestBreed = getParentsSpecies(dto.getFatherName(), dto.getMotherName());
        return breedable.createBaby(requestBreed);
    }

    public RequestBreed getParentsSpecies(String fatherName, String motherName) {
        Herd herd = herdRepository.findHerd();

        String fatherSpecies;
        String motherSpecies;

        if (herd.findDinosaurByName(fatherName) == null) {
            throw new NotExistentNameException();
        }

        if (herd.findDinosaurByName(motherName) == null) {
            throw new NotExistentNameException();
        }

        if (!Objects.equals(herd.findDinosaurByName(fatherName).getGender(), "m")) {
            throw new InvalidFatherException();
        }

        if (!Objects.equals(herd.findDinosaurByName(motherName).getGender(), "f")) {
            throw new InvalidMotherException();
        }

        Dinosaur dinosaurFather = herd.findDinosaurByName(fatherName);
        fatherSpecies = dinosaurFather.getSpecies();

        Dinosaur dinosaurMother = herd.findDinosaurByName(motherName);
        motherSpecies = dinosaurMother.getSpecies();

        return babyAssembler.toExternalDto(fatherSpecies, motherSpecies);
    }
}
