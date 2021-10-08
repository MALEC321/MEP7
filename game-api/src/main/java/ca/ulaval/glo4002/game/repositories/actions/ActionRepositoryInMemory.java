package ca.ulaval.glo4002.game.repositories.actions;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;

import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryInMemory implements ActionRepository {
    private final List<Action> waitingActions = new ArrayList<>();

    @Override
    public void save(Action action) {
        waitingActions.add(action);
    }

    @Override
    public List<Action> getWaitingActions() {
        return waitingActions;
    }

    @Override
    public void execute() {
        for (Action action: waitingActions) {
            action.execute();
        }

        waitingActions.clear();
    }

    @Override
    public void reset() {
        waitingActions.clear();
    }
}
