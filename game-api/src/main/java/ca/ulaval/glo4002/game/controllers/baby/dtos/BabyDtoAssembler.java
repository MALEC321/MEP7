package ca.ulaval.glo4002.game.controllers.baby.dtos;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyDto;

public class BabyDtoAssembler {
    public BabyCreationDto fromRequest(BabyRequest request) {
        return new BabyCreationDto(request.getName(), request.getFatherName(), request.getMotherName());
    }

    public BabyResponse toResponse(BabyDto babyDto) {
        return new BabyResponse(babyDto.getName(), babyDto.getFatherName(), babyDto.getMotherName());
    }
}
