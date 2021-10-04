package ca.ulaval.glo4002.game.repositories.actions;

import ca.ulaval.glo4002.game.domain.actions.Action;

import java.util.List;

public interface ActionRepository {
    void save(Action action);
    List<Action> getWaitingActions();
    void execute();
    void reset();
}
