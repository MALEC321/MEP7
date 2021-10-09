package ca.ulaval.glo4002.game.domain.turn;

import ca.ulaval.glo4002.game.domain.actions.Action;

import java.util.List;
import java.util.UUID;

public class TurnFactory {

    public TurnFactory() {
    }

    public Turn create(List<Action> actions) {

        return new Turn(UUID.randomUUID(), actions);
    }
}
