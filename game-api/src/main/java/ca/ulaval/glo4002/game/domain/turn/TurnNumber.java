package ca.ulaval.glo4002.game.domain.turn;

public class TurnNumber {
    private final int number;

    public TurnNumber(int turn) {
        this.number = turn;
    }

    public int getNumber() {
        return number;
    }

    public TurnNumber nextTurnNumber() {
        return new TurnNumber(this.number + 1);
    }
}
