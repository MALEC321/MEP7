package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Turn> turns;

    public Game() {
        turns = new ArrayList<>();
    }

    public TurnNumber nextTurnNumber() {
        return lastTurnNumber().nextTurnNumber();
    }

    public TurnNumber lastTurnNumber() {
        return (isFistTurn()) ? new TurnNumber(0) : turns.get(turns.size() - 1).getTurnNumber();
    }

    public Turn findByNumber(TurnNumber turnNumber) {
        for (Turn turnFound : turns) {
            if (turnFound.getTurnNumber().equals(turnNumber)) {
                return turnFound;
            }
        }
        return null;
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    private boolean isFistTurn() {
        return turns.size() == 0;
    }
}
