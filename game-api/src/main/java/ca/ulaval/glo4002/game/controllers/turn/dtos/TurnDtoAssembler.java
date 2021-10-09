package ca.ulaval.glo4002.game.controllers.turn.dtos;

public class TurnDtoAssembler {

    public TurnResponse toResponse(TurnDto turnDto) {
        TurnResponse response = new TurnResponse();
        response.turnNumber = turnDto.turnNumber;

        return response;
    }
}
