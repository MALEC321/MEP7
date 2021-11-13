package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class TurnFactory {
    public Turn create(TurnNumber turnNumber, List<Action> actions) {
        return new Turn(turnNumber, actions);
    }
}
