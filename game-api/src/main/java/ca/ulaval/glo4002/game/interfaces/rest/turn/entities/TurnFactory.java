package ca.ulaval.glo4002.game.interfaces.rest.turn.entities;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Actions;

import java.util.List;
import java.util.UUID;

public class TurnFactory {

    public TurnFactory() {
    }

    public Turn create(List<Actions> actions) {

        return new Turn(UUID.randomUUID(), actions);
    }
}
