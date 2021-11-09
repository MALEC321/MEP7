package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.*;

import java.util.Arrays;
import java.util.List;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private Pantry pantry = new Pantry();

    @Override
    public void add(Resources resources) {
        pantry.add(resources);
    }

    @Override
    public List<ResourcesGroup> findAll() {
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
        return pantry.removeResourceQty(type, quantity);
    }

    @Override
    public void reset() {
        pantry.clear();
    }
}
