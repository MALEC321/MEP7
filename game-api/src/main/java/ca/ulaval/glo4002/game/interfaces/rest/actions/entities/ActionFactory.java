package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public class ActionFactory {

    public Action create(ResourceElements resource, Command command, ResourceRepository resourceRepository) {
        return (command == Command.ADD)? new AddResource(UUID.randomUUID(), resource, resourceRepository) :
                new RetrieveAction(UUID.randomUUID(), resource, resourceRepository);
    }
}
