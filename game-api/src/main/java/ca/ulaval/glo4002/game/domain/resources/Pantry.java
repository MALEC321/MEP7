package ca.ulaval.glo4002.game.domain.resources;

import java.util.LinkedList;
import java.util.Queue;

public class Pantry {
    private Queue<Burger> burgerQueue = new LinkedList<>();
    private Queue<Salad> saladQueue = new LinkedList<>();
    private Queue<Water> waterQueue = new LinkedList<>();

    private final Resource consumedResources = new Resource(new Burger(0), new Salad(0), new Water(0));
    private final Resource expiredResources = new Resource(new Burger(0), new Salad(0), new Water(0));

    public Resource findFreshResource(){
        return new Resource(new Burger(findResourceQuantity(0)), new Salad(findResourceQuantity(1)),new Water(findResourceQuantity(2)));
    }

    public int findResourceQuantity(int value) {
        int quantity = 0;

        if (value == 0) {
            for (Burger burger: burgerQueue) {
                quantity += burger.getQuantity();
            }

            return quantity;
        }  else if (value == 1) {
            for (Salad salad: saladQueue) {
                quantity += salad.getQuantity();
            }

            return quantity;
        }  else {
            for (Water water: waterQueue) {
                quantity += water.getQuantity();
            }

            return quantity;
        }
    }

    public void add(ResourceElements resourceElements) {
        if (resourceElements instanceof Burger) burgerQueue.add((Burger)resourceElements);
        if (resourceElements instanceof Salad) saladQueue.add((Salad)resourceElements);
        if (resourceElements instanceof Water) waterQueue.add((Water)resourceElements);
    }

    public boolean eatBurger(int quantity) {
        for (ResourceElements burgers: burgerQueue) {
            int actualQuantity = burgers.getQuantity();

            if (burgers.removeElement(quantity)) {
                consumedResources.addBurger(quantity);

                return true;
            }

            consumedResources.addBurger(actualQuantity);
            quantity -= actualQuantity;
        }

        return false;
    }

    public boolean eatSalad(int quantity) {
        for (ResourceElements salad: saladQueue) {
            int actualQuantity = salad.getQuantity();

            if (salad.removeElement(quantity)) {
                consumedResources.addSalad(quantity);

                return true;
            }

            consumedResources.addSalad(actualQuantity);
            quantity -= actualQuantity;
        }

        return false;
    }

    public boolean drinkWater(int quantity) {
        for (ResourceElements water: waterQueue) {
            int actualQuantity = water.getQuantity();

            if (water.removeElement(quantity)) {
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
    }
}
