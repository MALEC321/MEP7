package ca.ulaval.glo4002.game.domain.game;

import ca.ulaval.glo4002.game.application.turn.NoTurnsToUnturnException;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

import java.util.ArrayList;
import java.util.List;

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

    public void removeLastTurn() {
        if (turns.size() == 1) {
            throw new NoTurnsToUnturnException();
        } else {
            turns.remove(turns.size() - 1);
        }
    }
}
