package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class FeedDinosaurs implements Action {

    private ResourcesDistributor resourcesDistributor;
    private Pantry pantry;
    private Herd herd;

    public FeedDinosaurs(ResourcesDistributor resourcesDistributor, Pantry pantry, Herd herd) {
        this.resourcesDistributor = resourcesDistributor;
        this.pantry = pantry;
        this.herd = herd;
    }

    public ResourcesDistributor getResourcesDistributor() {
        return resourcesDistributor;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public Herd getHerd() {
        return herd;
    }

    @Override
    public void execute() {
        resourcesDistributor.feedDinosaurs(pantry, herd);
    }
}
