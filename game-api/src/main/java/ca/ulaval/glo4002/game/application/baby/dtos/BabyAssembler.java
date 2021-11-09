package ca.ulaval.glo4002.game.application.baby.dtos;

import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;

public class BabyAssembler {
    public BabyDto toDto(DinosaurBaby bebe) {
        BabyDto dto = new BabyDto();
        dto.name = bebe.getName();
        dto.fatherName = bebe.getFatherName();
        dto.motherName = bebe.getMotherName();
        return dto;
    }

    public ExternalApiCreationDto toExternalDto(String fatherSpecies, String motherSpecies) {
        ExternalApiCreationDto dto = new ExternalApiCreationDto();
        dto.fatherSpecies = fatherSpecies;
        dto.motherSpecies = motherSpecies;
        return dto;
    }
}
