package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;

public class AddResource implements Action {

    private ResourceElements resourceElements;
    private Pantry pantry;

    public AddResource(ResourceElements resourceElements, Pantry pantry) {
        this.resourceElements = resourceElements;
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        pantry.add(resourceElements);
    }
}
