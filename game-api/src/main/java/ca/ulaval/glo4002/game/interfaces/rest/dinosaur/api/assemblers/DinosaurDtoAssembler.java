package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.dtos.DinosaurResponseItem;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.dtos.DinosaursResponse;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurFactory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidGenderException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidSpeciesException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidWeightException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DinosaurDtoAssembler {
    DinosaurFactory dinosaurFactory;

    public DinosaurCreationDto fromRequest(DinosaurRequest request) {
        DinosaurCreationDto dto = new DinosaurCreationDto();

//        try {
//            dinosaurFactory.validateName(request.name);
//        } catch (IllegalArgumentException ex) {
//            throw new DuplicateNameException();
//        }
        if (request.weight <= 0) {
            throw new InvalidWeightException();
        }
        if ((!"f".equals(request.gender)) && (!"m".equals(request.gender))) {
            throw new InvalidGenderException();
        }

        if (!SpeciesDiet.contains(request.species)) {
            throw new InvalidSpeciesException();
        }

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
