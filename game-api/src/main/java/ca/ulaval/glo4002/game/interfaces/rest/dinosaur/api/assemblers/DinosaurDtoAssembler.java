package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.dtos.DinosaurResponse;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;

public class DinosaurDtoAssembler {

    public DinosaurResponse toResponse(DinosaurDto dinosaurDto) {
        DinosaurResponse response = new DinosaurResponse();
        response.name = dinosaurDto.name;
        response.weight = dinosaurDto.weight;
        response.gender = dinosaurDto.gender;
        response.species = dinosaurDto.species;

        return response;
    }
}
