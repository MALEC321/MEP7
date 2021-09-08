package ca.ulaval.glo4002.game.interfaces.rest.turn.entities;

import java.util.List;
import java.util.UUID;

public class Turn {
    private UUID id;
    private int number;
    private List<Object> actions;

    public Turn(UUID id, int number, List<Object> actions) {
        this.number = number;
        this.actions = actions;
    }
}
