package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class RemoveAllEmptyResourcesAction implements Action {

    private Pantry pantry;

    public RemoveAllEmptyResourcesAction(Pantry pantry) {
        this.pantry = pantry;
    }

    public Pantry getPantry() {
        return pantry;
    }

    @Override
    public void execute() {
        pantry.removeAllEmptyResources();
    }
}
