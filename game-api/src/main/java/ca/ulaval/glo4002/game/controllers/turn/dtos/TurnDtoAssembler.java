package ca.ulaval.glo4002.game.controllers.turn.dtos;

public class TurnDtoAssembler {

    public TurnResponse toResponse(TurnDto turnDto) {
        return new TurnResponse(turnDto.getNoTurn());
    }
}
