package ca.ulaval.glo4002.game.application.bebe.dtos;

import ca.ulaval.glo4002.game.controllers.bebe.dtos.ExternalApiCreationDto;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;

import java.util.List;

public class BebeAssembler {

    public BebeDto toDto(DinosaurBaby bebe) {
        BebeDto dto = new BebeDto();
        dto.name = bebe.getName();
        dto.fatherName = bebe.getFatherName();
        dto.motherName = bebe.getMotherName();
        return dto;
    }

    public ExternalApiCreationDto toDto(String fatherSpecies, String motherSpecies) {
        ExternalApiCreationDto dto = new ExternalApiCreationDto();
        dto.fatherSpecies = fatherSpecies;
        dto.motherSpecies = motherSpecies;
        return dto;
    }
}
