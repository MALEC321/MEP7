package ca.ulaval.glo4002.game.interfaces.rest.turn.api.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos.TurnResponse;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;

public class TurnDtoAssembler {

    public TurnResponse toResponse(TurnDto turnDto) {
        TurnResponse response = new TurnResponse();
        response.turnNumber = turnDto.turnNumber;

        return response;
    }
}
