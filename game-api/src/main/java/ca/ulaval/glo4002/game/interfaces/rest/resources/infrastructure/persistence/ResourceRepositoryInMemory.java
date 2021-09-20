package ca.ulaval.glo4002.game.interfaces.rest.resources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.*;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final Queue<ResourceElements> resourceInventory = new LinkedList<>();
    private final List<ResourceElements> consumedResources = new ArrayList<>();
    private final List<ResourceElements> expiredResources = new ArrayList<>();

    @Override
    public void add(ResourceElements resource) {
        resourceInventory.add(resource);
    }

    @Override
    public void removeStale() {
        resourceInventory.remove();
    }

    @Override
    public Queue<ResourceElements> findAll(){
        return resourceInventory;
    }

}