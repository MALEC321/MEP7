package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

public class AddResource extends Action {
    public AddResource(UUID id, Resources resources, ResourceRepository resourceRepository) {
        super(id, resources, resourceRepository);
    }

    @Override
    public void execute() {
        ((ResourceRepository) getObjectRepository()).add((Resources) getObject());
    }
}
