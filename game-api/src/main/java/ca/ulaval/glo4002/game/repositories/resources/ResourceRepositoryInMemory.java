package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.domain.resources.*;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.Arrays;
import java.util.List;

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
