package ca.ulaval.glo4002.game.controllers.bebe.dtos;

import ca.ulaval.glo4002.game.domain.bebe.BebeFactory;

public class BebeDtoAssembler {

    BebeFactory bebeFactory;

    public BebeCreationDto fromRequest(BebeRequest request) {
        BebeCreationDto dto = new BebeCreationDto();
        dto.name = request.name;
        dto.fatherName = request.fatherName;
        dto.motherName = request.motherName;

        return dto;
    }

    public BebeResponse toResponse(BebeDto bebeDto) {
        BebeResponse response = new BebeResponse();
        response.name = bebeDto.name;
        response.fatherName = bebeDto.fatherName;
        response.motherName = bebeDto.motherName;

        return response;
    }
}
