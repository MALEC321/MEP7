package ca.ulaval.glo4002.game.interfaces.rest.manageresources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities.Resource;
import ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities.ResourceRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final Map<UUID, Resource> inventory = new HashMap<>();

    @Override
    public void add(Resource resource) {
        inventory.put(resource.getId(), resource);
    }

    @Override
    public Resource removeStale(UUID id) {
        return inventory.remove(id);
    }
}
