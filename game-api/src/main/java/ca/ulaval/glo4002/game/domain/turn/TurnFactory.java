package ca.ulaval.glo4002.game.domain.turn;

import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class TurnFactory {
    public Turn create(List<Action> actions) {
        return new Turn(UUID.randomUUID(), actions);
    }
}
