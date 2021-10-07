package ca.ulaval.glo4002.game.controllers.baby.dtos;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyDto;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyRequest;

public class BabyDtoAssembler {

    public BabyCreationDto fromRequest(BabyRequest request) {
        BabyCreationDto dto = new BabyCreationDto();
        dto.name = request.name;
        dto.fatherName = request.fatherName;
        dto.motherName = request.motherName;

        return dto;
    }

    public BabyResponse toResponse(BabyDto babyDto) {
        BabyResponse response = new BabyResponse();
        response.name = babyDto.name;
        response.fatherName = babyDto.fatherName;
        response.motherName = babyDto.motherName;

        return response;
    }
}
