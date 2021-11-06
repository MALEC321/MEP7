package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.turn.aggregate.TurnId;

public class TurnFactory {

    public TurnId create(String turnId, List<Action> actions) {
        return new TurnId(turnId, actions);
    }
}
