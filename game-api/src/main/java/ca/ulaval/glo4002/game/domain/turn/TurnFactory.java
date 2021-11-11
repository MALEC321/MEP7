package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.turn.aggregate.Turn;

public class TurnFactory {
    public Turn create(String turnNumber, List<Action> actions) {
        return new Turn(turnNumber, actions);
    }
}
