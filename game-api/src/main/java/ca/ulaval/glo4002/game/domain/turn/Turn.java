package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class Turn {

    private final String turnNumber;
    private List<Action> actions;

    public Turn(String turnNumber) {
        this.turnNumber = turnNumber;
    }

    public Turn(String turnNumber, List<Action> actions) {
        this.turnNumber = turnNumber;
        this.actions = actions;
    }

    public String getTurnNumber() {
        return this.turnNumber;
    }

    public List<Action> getActions() {
        return actions;
    }
}
