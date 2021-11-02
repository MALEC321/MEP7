package ca.ulaval.glo4002.game.application.baby.dtos;

import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;

public class BabyAssembler {

    public BabyDto toDto(DinosaurBaby bebe) {
        return new BabyDto(bebe.getName(), bebe.getFatherName(), bebe.getMotherName());
    }

    public ExternalApiCreationDto toExternalDto(String fatherSpecies, String motherSpecies) {
        return new ExternalApiCreationDto(fatherSpecies, motherSpecies);
    }
}
