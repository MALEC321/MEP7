package ca.ulaval.glo4002.game.interfaces.rest.turn.api.dtos;

import java.util.Collections;
import java.util.List;

public class TurnRequest {
    private final String defaultAction = "+something";
    public List<Object> actions = List.of(defaultAction);
}
