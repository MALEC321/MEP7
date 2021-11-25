package ca.ulaval.glo4002.game.application.baby;

import java.util.Objects;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;

public class ParentInformationCenter {
    private final HerdRepository herdRepository;
    private final BabyAssembler babyAssembler;

    public ParentInformationCenter(HerdRepository herdRepository, BabyAssembler babyAssembler) {
        this.herdRepository = herdRepository;
        this.babyAssembler = babyAssembler;
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
