package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;

public class AddResource extends Action {
    public AddResource(UUID id, Resources resources, PantryRepository resourceRepository) {
        super(id, resources, resourceRepository);
    }

    @Override
    public void execute() {
        ((PantryRepository) getObjectRepository()).findPantry().addResources((Resources) getObject());
    }
}
