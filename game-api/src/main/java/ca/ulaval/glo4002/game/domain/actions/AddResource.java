package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;

public class AddResource implements Action {

    private Resources resources;
    private Pantry pantry;

    public AddResource(Resources resources, Pantry pantry) {
        this.resources = resources;
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        pantry.addResources(resources);
    }
}
