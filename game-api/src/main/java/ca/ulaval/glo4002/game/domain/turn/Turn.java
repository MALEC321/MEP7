package ca.ulaval.glo4002.game.domain.turn;

import ca.ulaval.glo4002.game.domain.actions.Action;

import java.util.List;
import java.util.UUID;

public class Turn {
    private final UUID id;
    public static int number = 0;
    private final List<Action> actions;

    public Turn(UUID id, List<Action> actions) {
        this.id = id;
        number++;
        this.actions = actions;
    }

    public UUID getId() {
        return id;
    }

    public List<Action> getActions() {
        return actions;
    }
}
