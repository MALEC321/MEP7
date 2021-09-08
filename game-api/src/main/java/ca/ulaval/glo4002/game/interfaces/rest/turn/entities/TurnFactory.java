package ca.ulaval.glo4002.game.interfaces.rest.turn.entities;

import java.util.List;
import java.util.UUID;

public class TurnFactory {
    public TurnFactory() {}

    public Turn create(int number, List<Object> actions) {
        return new Turn(UUID.randomUUID(), number, actions);
    }
}
