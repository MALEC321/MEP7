package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;

import java.util.ArrayList;
import java.util.List;

public class DinosaurDtoAssembler {
    public DinosaurCreationDto fromRequest(DinosaurRequest request) {
        return new DinosaurCreationDto(request.getName(), request.getWeight(), request.getGender(), request.getSpecies());
    }

    public DinosaurResponse toResponse(DinosaurDto dinosaurDto) {
        return new DinosaurResponse(dinosaurDto.getName(), dinosaurDto.getWeight(), dinosaurDto.getGender(),
                dinosaurDto.getSpecies());
    }

    public DinosaursResponse toResponse(List<DinosaurDto> dinosaurDtos) {
        List<DinosaurResponse> responseItems = new ArrayList<>();
        for (DinosaurDto dinosaurDto : dinosaurDtos) {
            responseItems.add(toResponse(dinosaurDto));
        }
        return new DinosaursResponse(responseItems);
    }
}

