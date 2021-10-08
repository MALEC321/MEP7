package ca.ulaval.glo4002.game.domain.actions;

import java.util.List;

public interface ActionRepository {
    void save(Action action);
    List<Action> getWaitingActions();
    void execute();
    void reset();
}
