package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public class RetrieveAction extends Action {
    public RetrieveAction(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        super(id, resource, resourceRepository);
    }

    @Override
    public void execute() {
        System.out.println("Get resource in repository and retrieve a value of it");
    }
}
