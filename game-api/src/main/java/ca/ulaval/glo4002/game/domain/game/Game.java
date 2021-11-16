package ca.ulaval.glo4002.game.domain.game;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

public class Game {
    private final List<Turn> turns;

    public Game() {
        turns = new ArrayList<>();

        turns.add(new Turn(new TurnNumber(1)));
    }

    public TurnNumber nextTurnNumber() {
        return currentTurnNumber().nextTurnNumber();
    }

    public Turn currentTurn() {
        return turns.get(turns.size() - 1);
    }

    public TurnNumber currentTurnNumber() {
        return turns.get(turns.size() - 1).getTurnNumber();
    }

    public TurnNumber lastPlayedTurnNumber() {
        return turns.get(turns.size() - 2).getTurnNumber();
    }

/*    private boolean isFistTurn() {
        return turns.size() == 0;
    }*/

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    public Turn findByNumber(TurnNumber turnNumber) {
        for (Turn turnFound : turns) {
            if (turnFound.getTurnNumber().equals(turnNumber)) {
                return turnFound;
            }
        }
        return null;
    }
}
