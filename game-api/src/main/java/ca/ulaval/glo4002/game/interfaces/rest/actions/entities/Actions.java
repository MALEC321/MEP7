package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public abstract class Actions {
    private final UUID id;
    private final ResourceElements resource;

    public Actions(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        this.id = id;
        this.resource = resource;
    }

    public UUID getId() {
        return id;
    }


    abstract public void execute();
}
