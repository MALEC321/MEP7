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
    public boolean eatBurger(int quantity) { //900
        return pantry.eatBurger(quantity);
    }

    @Override
    public boolean eatSalad(int quantity) {
        return pantry.eatSalad(quantity);
    }

    @Override
    public boolean drinkWater(int quantity) {
        return pantry.drinkWater(quantity);
    }

    @Override
    public void reset() {
        pantry.clear();
    }
}
