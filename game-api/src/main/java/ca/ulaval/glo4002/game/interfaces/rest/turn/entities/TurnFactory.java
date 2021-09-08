package ca.ulaval.glo4002.game.interfaces.rest.turn.entities;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TurnFactory {

    public static int number = 0;
    public TurnFactory() {}

    public Turn create(List<Object> actions) {
        return new Turn(UUID.randomUUID(), number++, actions);
    }
}
