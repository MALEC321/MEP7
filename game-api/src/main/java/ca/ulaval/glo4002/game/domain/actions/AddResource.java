package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.UUID;

public class AddResource extends Action {
    public AddResource(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        super(id, resource, resourceRepository);
    }

    @Override
    public void execute() {
        ((ResourceRepository)getObjectRepository()).add((ResourceElements) getObject());
    }
}