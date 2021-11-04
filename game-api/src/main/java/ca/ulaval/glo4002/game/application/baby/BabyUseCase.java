package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.baby.breed.BabyCreationClient;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.repositories.client.dto.ResponseBreed;
import ca.ulaval.glo4002.game.repositories.client.dto.RequestBreed;
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
    private final BabyCreationClient babyCreationClient;

    public BabyUseCase(DinosaurRepository dinosaurRepository, BabyAssembler babyAssembler, ActionRepository actionRepository, ActionFactory actionFactory,
                       DinosaurFactory dinosaurFactory, BabyCreationClient babyCreationClient) {
        this.dinosaurRepository = dinosaurRepository;
        this.babyAssembler = babyAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
        this.dinosaurFactory = dinosaurFactory;
        this.babyCreationClient = babyCreationClient;
    }

    public void createBebe(BabyCreationDto dto) {
        RequestBreed requestBreed = getParentsSpecies(dto.getFatherName(), dto.getMotherName());
        Optional<ResponseBreed> bebeDto = babyCreationClient.createBaby(requestBreed);
        if(bebeDto.isPresent()) {
            Dinosaur baby = dinosaurFactory.create(dto.getName(), dto.getFatherName(), dto.getMotherName(),
                    bebeDto.get().getGender(), bebeDto.get().getOffspring());
            actionRepository.save(actionFactory.create(baby, dinosaurRepository));
        }
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
