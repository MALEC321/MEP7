package ca.ulaval.glo4002.game.controllers.turn.dtos;

import ca.ulaval.glo4002.game.domain.turn.Turn;

public class TurnAssembler {

    public TurnDto toDto(Turn turn) {
        TurnDto dto = new TurnDto();
        dto.turnNumber = Turn.number;

        return dto;
    }
}
