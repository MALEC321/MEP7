package ca.ulaval.glo4002.game.controllers.turn.dtos;

public class TurnResponse {
    private int turnNumber;

    public TurnResponse() {
    }

    public TurnResponse(int turnNumber) {
        this.turnNumber = turnNumber;
    }
    public int getTurnNumber() {
        return turnNumber;
    }
}
