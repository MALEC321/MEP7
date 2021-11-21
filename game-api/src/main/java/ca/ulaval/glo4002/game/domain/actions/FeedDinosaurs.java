package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class FeedDinosaurs implements Action {

    private ResourcesDistributor resourcesDistributor;
    private FoodContainer foodContainer;
    private Herd herd;

    public FeedDinosaurs(ResourcesDistributor resourcesDistributor, FoodContainer foodContainer, Herd herd) {
        this.resourcesDistributor = resourcesDistributor;
        this.foodContainer = foodContainer;
        this.herd = herd;
    }

    @Override
    public void execute() {
        resourcesDistributor.feedDinosaurs(foodContainer, herd);
    }
}
