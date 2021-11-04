package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.domain.resources.*;

import java.util.Arrays;
import java.util.List;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private Pantry pantry = new Pantry();

    @Override
    public void add(Resource resource) {
        pantry.add(resource);
    }

    @Override
    public List<Resources> findAll() {
        return Arrays.asList(pantry.findFreshResource(), pantry.getExpiredResources(), pantry.getConsumedResources());
    }

    @Override
    public Pantry getPantry() {
        return pantry;
    }

    @Override
    public int getFreshResourceQuantity(ResourceType resourceType) {
        return pantry.getFreshResourceQuantity(resourceType);
    }

    @Override
    public void decreaseExpirationDate() {
        pantry.decreaseExpirationDate();
    }

    @Override
    public boolean removeResources(ResourceType type, int quantity) {
        return pantry.removeResource(type, quantity);
    }

    @Override
    public void reset() {
        pantry.clear();
    }
}
