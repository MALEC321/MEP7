package ca.ulaval.glo4002.game.repositories.resources;

import java.util.Arrays;
import java.util.List;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceType;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private Pantry pantry = new Pantry();

    @Override
    public void add(ResourceElements resourceElements) {
        pantry.add(resourceElements);
    }

    @Override
    public List<Resource> findAll() {
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
