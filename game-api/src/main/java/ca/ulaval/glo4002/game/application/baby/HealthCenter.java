package ca.ulaval.glo4002.game.application.baby;

import java.util.Optional;

import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

public class HealthCenter {
    private final Breedable breedable;
    private final ParentInformationCenter parentInformationCenter;

    public HealthCenter(ParentInformationCenter parentInformationCenter, Breedable breedable) {
        this.breedable = breedable;
        this.parentInformationCenter = parentInformationCenter;
    }

    public Optional<ResponseBreed> tryToGiveBirthToBaby(BabyCreationDto dto) {
        RequestBreed requestBreed = parentInformationCenter.getParentsSpecies(dto.getFatherName(), dto.getMotherName());
        return breedable.createBaby(requestBreed);
    }
}
