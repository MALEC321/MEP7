package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;

public class Turns {
    private ArrayList<Turn> turns;

    public Turns() {
        turns = new ArrayList<>();
    }

    public String nextTurnNumber() {
        return String.valueOf(numberOfTurns() + 1);
    }

    public int numberOfTurns() {
        return turns.size();
    }

    public Turn findByNumber(String turnNumber) {
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
