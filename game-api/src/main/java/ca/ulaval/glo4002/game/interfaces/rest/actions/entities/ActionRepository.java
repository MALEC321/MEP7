package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.List;

public interface ActionRepository {
    void save(Action action);
    List<Action> getWaitingActions();
    void execute();
}
