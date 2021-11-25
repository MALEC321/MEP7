package ca.ulaval.glo4002.game.controllers.turn.dtos;

import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

public class TurnDto {
    private final TurnNumber noTurn;

    public TurnDto(TurnNumber noTurn) {
        this.noTurn = noTurn;
    }

    public int getNoTurn() {
        return noTurn.getNumber();
    }
}
