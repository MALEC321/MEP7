package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class ActionFactory {
    public Action createAddDinoAction(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public Action createAddResourceAction(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }

    public Action createCookItAction(Pantry pantry, ResourcesFactory resourcesFactory) {
        return new CookItAction(pantry, resourcesFactory);
    }

    public Action createRemoveAllExpiredResourcesAction(Pantry pantry) {
        return new RemoveAllExpiredResourcesAction(pantry);
    }

    public Action createRemoveAllEmptyResourcesAction(Pantry pantry) {
        return new RemoveAllEmptyResourcesAction(pantry);
    }

    public Action createRemoveOrphanedBabyDinosaursAction(Herd herd) {
        return new RemoveOrphanedBabyDinosaurs(herd);
    }
}
