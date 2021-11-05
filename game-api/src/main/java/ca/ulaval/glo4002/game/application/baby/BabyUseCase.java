package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.exceptions.types.InvalidFatherException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidMotherException;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

import java.util.Objects;
import java.util.Optional;

public class BabyUseCase {

    private final DinosaurRepository dinosaurRepository;
    private final BabyAssembler babyAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;
    private final DinosaurFactory dinosaurFactory;
    private final Breedable breedable;

    public BabyUseCase(DinosaurRepository dinosaurRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory,
                       DinosaurFactory dinosaurFactory, Breedable breedable) {
        this.dinosaurRepository = dinosaurRepository;
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
        if(babyDto.isPresent()) {
            Dinosaur baby = dinosaurFactory.create(dto.getName(), dto.getFatherName(), dto.getMotherName(),
                    babyDto.get().getGender(), babyDto.get().getOffspring());
            actionRepository.save(actionFactory.create(baby, dinosaurRepository));
        }
    }

    private Optional<ResponseBreed> tryToGiveBirthToBaby(BabyCreationDto dto) {
        RequestBreed requestBreed = getParentsSpecies(dto.getFatherName(), dto.getMotherName());
        Optional<ResponseBreed> babyDto = breedable.createBaby(requestBreed);
        return babyDto;
    }

    private RequestBreed getParentsSpecies(String fatherName, String motherName) {
        String fatherSpecies;
        String motherSpecies;

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
}
