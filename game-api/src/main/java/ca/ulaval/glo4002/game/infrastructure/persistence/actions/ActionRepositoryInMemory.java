package ca.ulaval.glo4002.game.infrastructure.persistence.actions;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;

import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryInMemory implements ActionRepository {
    private final List<Action> actionList = new ArrayList<>();

    @Override
    public void save(Action action) {
        actionList.add(action);
    }

    @Override
    public List<Action> getActionList() {
        return actionList;
    }

    @Override
    public void executeActions() {
        for (Action action : actionList) {
            action.execute();
        }
        actionList.clear();
    }

    @Override
    public void reset() {
        actionList.clear();
    }
}
