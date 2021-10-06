package ca.ulaval.glo4002.game.application.bebe.dtos;

import ca.ulaval.glo4002.game.application.bebe.dtos.BebeDto;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;

public class BebeAssembler {

    public BebeDto toDto(DinosaurBaby bebe) {
        BebeDto dto = new BebeDto();
        dto.name = bebe.getName();
        dto.fatherName = bebe.getFatherName();
        dto.motherName = bebe.getMotherName();
        return dto;
    }
}
