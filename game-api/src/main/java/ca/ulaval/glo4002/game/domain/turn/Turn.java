package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class Turn {
    private final TurnNumber turnNumber;
    private final List<Action> actions;

    public Turn(TurnNumber turnNumber) {
        this.turnNumber = turnNumber;
        this.actions = new ArrayList<>();
    }

    public Turn(TurnNumber turnNumber, List<Action> actions) {
        this.turnNumber = turnNumber;
        this.actions = actions;
    }

    public TurnNumber getTurnNumber() {
        return this.turnNumber;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void executeActions() {
        for (Action action : actions) {
            action.execute();
        }
        actions.clear();
    }
}
