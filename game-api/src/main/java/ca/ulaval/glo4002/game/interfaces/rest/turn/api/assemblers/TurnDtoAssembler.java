package ca.ulaval.glo4002.game.interfaces.rest.turn.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnRequest;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;

public class TurnDtoAssembler {

    public TurnCreationDto fromRequest(TurnRequest request) {
        TurnCreationDto dto = new TurnCreationDto();
        dto.actions = request.actions;

        return dto;
    }

    public TurnResponse toResponse(TurnDto turnDto) {
        TurnResponse response = new TurnResponse();
        response.turnNumber = turnDto.turnNumber;

        return response;
    }
}
