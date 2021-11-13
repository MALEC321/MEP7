package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Turn> turns;

    public Game() {
        turns = new ArrayList<>();
    }

    public TurnNumber nextTurnNumber() {
        TurnNumber lastTurnNumber = (turns.size() == 0) ? new TurnNumber(0) : turns.get(turns.size() - 1).getTurnNumber();
        return lastTurnNumber.nextTurnNumber();
    }

    public TurnNumber numberOfTurns() {
        return turns.get(turns.size() - 1).getTurnNumber();
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
}
