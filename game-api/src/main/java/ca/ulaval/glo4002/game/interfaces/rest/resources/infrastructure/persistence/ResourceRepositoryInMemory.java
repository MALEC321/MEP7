package ca.ulaval.glo4002.game.interfaces.rest.resources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.*;
import org.javatuples.Triplet;

import java.util.*;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final Queue<ResourceElements> resourceInventory = new LinkedList<>();
    private final Triplet<Queue<Burger>, Queue<Salad>, Queue<Water>> resources = Triplet.with(new LinkedList<>(),new LinkedList<>(), new LinkedList<>()) ;
    private final Resource consumedResources = new Resource(new Burger(0), new Salad(0), new Water(0));
    private final Resource expiredResources = new Resource(new Burger(0), new Salad(0), new Water(0));

    @Override
    public void add(ResourceElements resourceElement) {
        if (resourceElement instanceof Burger) resources.getValue0().add((Burger)resourceElement);
        if (resourceElement instanceof Salad) resources.getValue1().add((Salad)resourceElement);
        if (resourceElement instanceof Water) resources.getValue2().add((Water)resourceElement);
    }

    @Override
    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();
        for (Burger burgersContainer: resources.getValue0()) {
            burgersContainer.decreaseExpirationDate();
        }

        for (Salad saladBowl : resources.getValue1()) {
            if (saladBowl.getQuantity() == 0) resources.getValue1().remove(saladBowl);
            saladBowl.decreaseExpirationDate();
        }

        for (Water waterBank: resources.getValue2()) {
            if (waterBank.getQuantity() == 0)  resources.getValue2().remove(waterBank);
            waterBank.decreaseExpirationDate();
        }
    }

    private void removeAllEmptyResources() {
        while (resources.getValue0().peek() != null && resources.getValue0().peek().getQuantity() == 0) {
            resources.getValue0().poll();
        }
        while (resources.getValue1().peek() != null && resources.getValue1().peek().getQuantity() == 0) {
            resources.getValue1().poll();
        }
        while (resources.getValue2().peek() != null && resources.getValue2().peek().getQuantity() == 0) {
            resources.getValue2().poll();
        }
    }

    private void removeAllExpiredResources() {
        while (resources.getValue0().peek() != null && resources.getValue0().peek().getDaysLeft() == 0) {
            assert resources.getValue0().peek() != null;
            expiredResources.addBurger(resources.getValue0().peek().getQuantity());
            resources.getValue0().poll();
        }

        while (resources.getValue1().peek() != null && resources.getValue1().peek().getDaysLeft() == 0) {
            assert resources.getValue1().peek() != null;
            expiredResources.addSalad(resources.getValue1().peek().getQuantity());
            resources.getValue1().poll();
        }

        while (resources.getValue2().peek() != null && resources.getValue2().peek().getDaysLeft() == 0) {
            assert resources.getValue2().peek() != null;
            expiredResources.addWater(resources.getValue2().peek().getQuantity());
            resources.getValue2().poll();
        }
    }

    @Override
    public Triplet<Queue<Burger>, Queue<Salad>, Queue<Water>> findAll(){
        return resources;
    }

    @Override
    public boolean consume(ResourceElements resourceElement, int quantity) {
        if (resourceElement instanceof Burger) {
            return eatBurger(quantity);
        }  else if (resourceElement instanceof Salad) {
            return eatSalad(quantity);
        }  else if (resourceElement instanceof Water) {
            return drinkWater(quantity);
        }

        return false;
    }

    private int findResourceQuantity(ResourceElements resourceElement) {
        int quantity = 0;
        if (resourceElement instanceof Burger) {
            for (Burger burger: resources.getValue0()) {
                quantity += burger.getQuantity();
            }
            return quantity;
        }  else if (resourceElement instanceof Salad) {
            for (Salad salad: resources.getValue1()) {
                quantity += salad.getQuantity();
            }
            return quantity;
        }  else {
            for (Water water: resources.getValue2()) {
                quantity += water.getQuantity();
            }
            return quantity;
        }
    }

    private boolean eatBurger(int quantity) {
        for (ResourceElements burgerBowl: resources.getValue0()) {
            int actualQuantity = burgerBowl.getQuantity();
            if (burgerBowl.removeElement(quantity)) {
                consumedResources.addBurger(quantity);
                return true;
            }
            quantity -= actualQuantity;
            consumedResources.addBurger(quantity);
        }
        return false;
    }

    private boolean eatSalad(int quantity) {
        for (ResourceElements saladBowl: resources.getValue1()) {
            int actualQuantity = saladBowl.getQuantity();
            if (saladBowl.removeElement(quantity)) {
                consumedResources.addSalad(quantity);
                return true;
            }
            quantity -= actualQuantity; //Because salad quantity will be 0 after removeElement
            consumedResources.addSalad(quantity);
        }
        return false;
    }

    private boolean drinkWater(int quantity) {
        for (ResourceElements waterBank: resources.getValue2()) {
            int actualQuantity = waterBank.getQuantity();
            if (waterBank.removeElement(quantity)) {
                consumedResources.addWater(quantity);
                return true;
            }
            quantity -= actualQuantity;
            consumedResources.addWater(quantity);
        }
        return false;
    }

}
