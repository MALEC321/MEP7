package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class RemoveAllExpiredResourcesAction implements Action {

    private Pantry pantry;

    public RemoveAllExpiredResourcesAction(Pantry pantry) {
        this.pantry = pantry;
    }

    public Pantry getPantry() {
        return pantry;
    }

    @Override
    public void execute() {
        pantry.removeAllEmptyResources();
        pantry.removeAllExpiredResources();
        pantry.decreaseExpirationDate();
    }
}
