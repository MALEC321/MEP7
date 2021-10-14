package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;

public class DinosaurDtoAssembler {
    DinosaurFactory dinosaurFactory;

    public DinosaurCreationDto fromRequest(DinosaurRequest request) {
        DinosaurCreationDto dto = new DinosaurCreationDto();
        dto.name = request.name;
        dto.weight = request.weight;
        dto.gender = request.gender;
        dto.species = request.species;

        return dto;
    }

    public DinosaurResponseItem toResponse(DinosaurDto dinosaurDto) {
        DinosaurResponseItem response = new DinosaurResponseItem();
        response.name = dinosaurDto.name;
        response.weight = dinosaurDto.weight;
        response.gender = dinosaurDto.gender;
        response.species = dinosaurDto.species;

        return response;
    }

    public DinosaursResponse toResponse(List<DinosaurDto> dinosaurDtos) {
        DinosaursResponse response = new DinosaursResponse();
        response.items = dinosaurDtos.stream().map(this::toResponse)
            .collect(Collectors.toList());

        return response;
    }
}
