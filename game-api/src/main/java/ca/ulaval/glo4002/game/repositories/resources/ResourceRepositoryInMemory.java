package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.domain.resources.*;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.Arrays;
import java.util.List;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final Pantry freshResources = new Pantry();
    private final Resource consumedResources = new Resource(new Burger(0), new Salad(0), new Water(0));
    private final Resource expiredResources = new Resource(new Burger(0), new Salad(0), new Water(0));

    @Override
    public void add(ResourceElements resourceElement) {
        if (resourceElement instanceof Burger) freshResources.burgerQueue.add((Burger)resourceElement);
        if (resourceElement instanceof Salad) freshResources.saladQueue.add((Salad)resourceElement);
        if (resourceElement instanceof Water) freshResources.waterQueue.add((Water)resourceElement);
    }

    @Override
    public Resource findConsumedResource() {
        return this.consumedResources;
    }

    @Override
    public Resource findExpiredResource() {
        return this.expiredResources;
    }

    @Override
    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();

        for (Burger burgersContainer: freshResources.burgerQueue) {
            burgersContainer.decreaseExpirationDate();
        }

        for (Salad saladBowl : freshResources.saladQueue) {
            saladBowl.decreaseExpirationDate();
        }

        for (Water waterBank: freshResources.waterQueue) {
            waterBank.decreaseExpirationDate();
        }
    }

    private void removeAllEmptyResources() {
        while (freshResources.burgerQueue.peek() != null && freshResources.burgerQueue.peek().getQuantity() == 0) {
            freshResources.burgerQueue.poll();
        }
        while (freshResources.saladQueue.peek() != null && freshResources.saladQueue.peek().getQuantity() == 0) {
            freshResources.saladQueue.poll();
        }
        while (freshResources.waterQueue.peek() != null && freshResources.waterQueue.peek().getQuantity() == 0) {
            freshResources.waterQueue.poll();
        }
    }

    private void removeAllExpiredResources() {
        while (freshResources.burgerQueue.peek() != null && freshResources.burgerQueue.peek().getDaysLeft() == 0) {
            assert freshResources.burgerQueue.peek() != null;
            expiredResources.addBurger(freshResources.burgerQueue.peek().getQuantity());
            freshResources.burgerQueue.poll();
        }

        while (freshResources.saladQueue.peek() != null && freshResources.saladQueue.peek().getDaysLeft() == 0) {
            assert freshResources.saladQueue.peek() != null;
            expiredResources.addSalad(freshResources.saladQueue.peek().getQuantity());
            freshResources.saladQueue.poll();
        }

        while (freshResources.waterQueue.peek() != null && freshResources.waterQueue.peek().getDaysLeft() == 0) {
            assert freshResources.waterQueue.peek() != null;
            expiredResources.addWater(freshResources.waterQueue.peek().getQuantity());
            freshResources.waterQueue.poll();
        }
    }

    @Override
    public List<Resource> findAll() {
        return Arrays.asList(findFreshResource(), findExpiredResource(), findConsumedResource());
    }

    @Override
    public Resource findFreshResource(){
        return new Resource(new Burger(findResourceQuantity(0)), new Salad(findResourceQuantity(1)),new Water(findResourceQuantity(2)));
    }

    @Override
    public int findResourceQuantity(int value) {
        int quantity = 0;

        if (value == 0) {
            for (Burger burger: freshResources.burgerQueue) {
                quantity += burger.getQuantity();
            }

            return quantity;
        }  else if (value == 1) {
            for (Salad salad: freshResources.saladQueue) {
                quantity += salad.getQuantity();
            }

            return quantity;
        }  else {
            for (Water water: freshResources.waterQueue) {
                quantity += water.getQuantity();
            }

            return quantity;
        }
    }

    @Override
    public boolean eatBurger(int quantityNeeded) { //900
        return freshResources.eatBurger(quantityNeeded, consumedResources);
    }

    @Override
    public boolean eatSalad(int quantity) {
        return freshResources.eatSalad(quantity, consumedResources);
    }

    @Override
    public boolean drinkWater(int quantity) {
        return freshResources.drinkWater(quantity, consumedResources);
    }

    @Override
    public void reset() {
        freshResources.burgerQueue.clear();
        freshResources.saladQueue.clear();
        freshResources.waterQueue.clear();
        consumedResources.clear();
        expiredResources.clear();
    }
}
