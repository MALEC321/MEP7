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
    public void decreaseExpirationDate() {
        pantry.decreaseExpirationDate();
    }

    @Override
    public boolean removeBurgers(int quantity) {
        return pantry.removeBurgers(quantity);
    }

    @Override
    public boolean removeSalads(int quantity) {
        return pantry.removeSalads(quantity);
    }

    @Override
    public boolean removeWater(int quantity) {
        return pantry.removeWater(quantity);
    }

    @Override
    public void reset() {
        pantry.clear();
    }
}
