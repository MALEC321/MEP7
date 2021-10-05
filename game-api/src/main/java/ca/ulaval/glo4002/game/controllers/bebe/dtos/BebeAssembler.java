package ca.ulaval.glo4002.game.controllers.bebe.dtos;

import ca.ulaval.glo4002.game.domain.bebe.Bebe;

public class BebeAssembler {

    public BebeDto toDto(Bebe bebe) {
        BebeDto dto = new BebeDto();
        dto.name = bebe.getName();
        dto.fatherName = bebe.getFatherName();
        dto.motherName = bebe.getMotherName();
        return dto;
    }
}
