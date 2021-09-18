package ca.ulaval.glo4002.game.interfaces.rest.actions.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Actions;

import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryInMemory implements ActionRepository {
    private final List<Actions> waitingActions = new ArrayList<>();

    @Override
    public void save(Actions action) {
        waitingActions.add(action);
    }

    @Override
    public List<Actions> getWaitingActions() {
        return waitingActions;
    }

    @Override
    public void execute() {
        for (Actions action : waitingActions) {
            action.execute();
        }
        waitingActions.clear();
    }
}
