package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public abstract class Actions {
    private final UUID id;
    private final ResourceElements resource;
    private final ResourceRepository resourceRepository;

    public Actions(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        this.id = id;
        this.resource = resource;
        this.resourceRepository = resourceRepository;
    }

    public UUID getId() {
        return id;
    }

    public ResourceRepository getResourceRepository() {
        return resourceRepository;
    }

    public ResourceElements getResource() {
        return resource;
    }

    abstract public void execute();
}
