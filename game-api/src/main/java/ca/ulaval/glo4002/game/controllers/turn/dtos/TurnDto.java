package ca.ulaval.glo4002.game.controllers.turn.dtos;

public class TurnDto {
    private int noTurn;

    public TurnDto(int noTurn) {
        this.noTurn = noTurn;
    }

    public int getNoTurn() {
        return noTurn;
    }
}
