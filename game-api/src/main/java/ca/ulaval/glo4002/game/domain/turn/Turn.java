package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class Turn {

    private final TurnNumber turnNumber;
    private List<Action> actions;

    public Turn( TurnNumber turnNumber){
        this.turnNumber = turnNumber;
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
}
