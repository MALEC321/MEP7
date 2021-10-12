package ca.ulaval.glo4002.game.domain.resources;

import java.util.LinkedList;
import java.util.Queue;

public class Pantry implements Eatable {
    private final Queue<Burger> burgerQueue = new LinkedList<>();
    private final Queue<Salad> saladQueue = new LinkedList<>();
    private final Queue<Water> waterQueue = new LinkedList<>();
    private final Resource consumedResources = new Resource();
    private final Resource expiredResources = new Resource();

    public Resource findFreshResource() {
        Resource resource = new Resource();

        for (Burger burger: burgerQueue) {
            resource.addBurger(burger.getQuantity());
        }

        for (Salad salad: saladQueue) {
            resource.addSalad(salad.getQuantity());
        }

        for (Water water: waterQueue) {
            resource.addWater(water.getQuantity());
        }

        return resource;
    }

    public void add(ResourceElements resourceElements) {
        if (resourceElements instanceof Burger) burgerQueue.add((Burger)resourceElements);
        if (resourceElements instanceof Salad) saladQueue.add((Salad)resourceElements);
        if (resourceElements instanceof Water) waterQueue.add((Water)resourceElements);
    }

    @Override
    public boolean remove(int quantity, ResourceType resourceType) {
        if (resourceType == ResourceType.BURGER) return removeBurgers(quantity);
        if (resourceType == ResourceType.SALAD) return removeSalads(quantity);
        return removeWater(quantity);
    }

    public boolean removeBurgers(int quantity) {
        for (ResourceElements burgers: burgerQueue) {
            int actualQuantity = burgers.getQuantity();

            boolean enoughQuantity = burgers.removeElement(quantity);
            if (enoughQuantity) {
                consumedResources.addBurger(quantity);
                return true;
            }

            consumedResources.addBurger(actualQuantity);
            quantity -= actualQuantity;
        }

        return false;
    }

    public boolean removeSalads(int quantity) {
        for (ResourceElements salad: saladQueue) {
            int actualQuantity = salad.getQuantity();

            boolean enoughQuantity = salad.removeElement(quantity);
            if (enoughQuantity) {
                consumedResources.addSalad(quantity);
                return true;
            }

            consumedResources.addSalad(actualQuantity);
            quantity -= actualQuantity;
        }

        return false;
    }

    public boolean removeWater(int quantity) {
        for (ResourceElements water: waterQueue) {
            int actualQuantity = water.getQuantity();

            boolean enoughQuantity = water.removeElement(quantity);
            if (enoughQuantity) {
                consumedResources.addWater(quantity);
                return true;
            }

            consumedResources.addWater(actualQuantity);
            quantity -= actualQuantity;
        }

        return false;
    }

    public void removeAllEmptyResources() {
        while (burgerQueue.peek() != null && burgerQueue.peek().isEmpty()) {
            burgerQueue.poll();
        }

        while (saladQueue.peek() != null && saladQueue.peek().isEmpty()) {
            saladQueue.poll();
        }

        while (waterQueue.peek() != null && waterQueue.peek().isEmpty()) {
            waterQueue.poll();
        }
    }

    public void removeAllExpiredResources() {
        while (burgerQueue.peek() != null && burgerQueue.peek().isExpired()) {
            assert burgerQueue.peek() != null;
            expiredResources.addBurger(burgerQueue.peek().getQuantity());
            burgerQueue.poll();
        }

        while (saladQueue.peek() != null && saladQueue.peek().isExpired()) {
            assert saladQueue.peek() != null;
            expiredResources.addSalad(saladQueue.peek().getQuantity());
            saladQueue.poll();
        }

        while (waterQueue.peek() != null && waterQueue.peek().isExpired()) {
            assert waterQueue.peek() != null;
            expiredResources.addWater(waterQueue.peek().getQuantity());
            waterQueue.poll();
        }
    }

    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();

        for (Burger burgersContainer: burgerQueue) {
            burgersContainer.decreaseExpirationDate();
        }

        for (Salad saladBowl : saladQueue) {
            saladBowl.decreaseExpirationDate();
        }

        for (Water waterBank: waterQueue) {
            waterBank.decreaseExpirationDate();
        }
    }

    public Resource getConsumedResources() {
        return consumedResources;
    }

    public Resource getExpiredResources() {
        return expiredResources;
    }

    public void clear() {
        burgerQueue.clear();
        saladQueue.clear();
        waterQueue.clear();

        expiredResources.clear();
        consumedResources.clear();
    }
}