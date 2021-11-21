package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class ActionFactory {
    public Action create(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public Action create(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }

    public Action create(Pantry pantry, ResourcesFactory resourcesFactory) {
        return new CookItAction(pantry, resourcesFactory);
    }

    public Action create(Pantry pantry) {
        return new DecreaseResourcesExpiration(pantry);
    }

    public Action create(ResourcesDistributor resourcesDistributor, FoodContainer foodContainer, Herd herd) {
        return new FeedDinosaurs(resourcesDistributor, foodContainer, herd);
    }

    public Action create(Herd herd) {
        return new RemoveOrphanedBabyDinosaurs(herd);
    }
}
