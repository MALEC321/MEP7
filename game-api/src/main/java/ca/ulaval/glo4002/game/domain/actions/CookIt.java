package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class CookIt implements Action {
    private final Pantry pantry;
    private final ResourcesFactory resourcesFactory;

    public CookIt(Pantry pantry, ResourcesFactory resourcesFactory) {
        this.pantry = pantry;
        this.resourcesFactory = resourcesFactory;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public ResourcesFactory getResourcesFactory() {
        return resourcesFactory;
    }

    @Override
    public void execute() {
        addCookitAction();
    }

    private void addCookitAction() {
        Resources burgers =  resourcesFactory.create(BURGER, 100);
        Resources salads =  resourcesFactory.create(SALAD, 250);
        Resources water =  resourcesFactory.create(WATER, 10000);

        pantry.addResources(burgers);
        pantry.addResources(salads);
        pantry.addResources(water);
    }
}
