package ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;

public class TurnAssembler {

    public TurnDto toDto(Turn turn) {
        TurnDto dto = new TurnDto();
        dto.turnNumber = turn.getNumber();

        return dto;
    }
}
