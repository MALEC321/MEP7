package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;

public class ActionFactory {
    public AddDino createAddDinoAction(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public AddResource createAddResourceAction(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }

    public CookIt createCookItAction(Pantry pantry, ResourcesFactory resourcesFactory) {
        return new CookIt(pantry, resourcesFactory);
    }

    public FeedDinosaurs createFeedDinosaursAction(Pantry pantry, Herd herd) {
        return new FeedDinosaurs(pantry, herd);
    }

    public RemoveExpiredResources createRemoveAllExpiredResourcesAction(Pantry pantry) {
        return new RemoveExpiredResources(pantry);
    }

    public RemoveOrphanedBabyDinosaurs createRemoveOrphanedBabyDinosaursAction(Herd herd) {
        return new RemoveOrphanedBabyDinosaurs(herd);
    }
}
