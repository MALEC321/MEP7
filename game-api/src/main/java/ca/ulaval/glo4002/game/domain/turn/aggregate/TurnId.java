package ca.ulaval.glo4002.game.domain.turn.aggregate;

import ca.ulaval.glo4002.game.domain.actions.Action;

import java.util.List;

public class TurnId {

    private String turnId;
    private List<Action> actions;

    public TurnId(String turnId) {
        this.turnId = turnId;
    }

    public TurnId(String turnId, List<Action> actions) {
        this.turnId = turnId;
        this.actions = actions;
    }

    public String getTurnId() {
        return this.turnId;
    }

    public List<Action> getActions() {
        return actions;
    }
}
