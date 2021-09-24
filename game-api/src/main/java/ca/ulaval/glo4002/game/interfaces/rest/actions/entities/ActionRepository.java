package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.List;

public interface ActionRepository {
    void save(Actions action);

    List<Actions> getWaitingActions();

    void execute();
}
