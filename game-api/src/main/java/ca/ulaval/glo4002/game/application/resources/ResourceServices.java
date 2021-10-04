package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.resources.*;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepository;
import org.javatuples.Triplet;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ResourceServices implements ResourceRepository {
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
            for (Burger burger: resources.getValue0()) {
                quantity += burger.getQuantity();
            }
            return quantity;
        }  else if (value == 1) {
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

    @Override
    public boolean eatBurger(int quantityNeeded) { //900
        for (ResourceElements burgerBowl: resources.getValue0()) {
            int actualQuantity = burgerBowl.getQuantity();
            if (burgerBowl.removeElement(quantityNeeded)) {
                consumedResources.addBurger(quantityNeeded);
                return true;
            }
            consumedResources.addBurger(actualQuantity);
            quantityNeeded -= actualQuantity;
        }
        return false;
    }

    @Override
    public boolean eatSalad(int quantity) {
        for (ResourceElements saladBowl: resources.getValue1()) {
            int actualQuantity = saladBowl.getQuantity();
            if (saladBowl.removeElement(quantity)) {
                consumedResources.addSalad(quantity);
                return true;
            }
            consumedResources.addSalad(actualQuantity);
            quantity -= actualQuantity; //Because salad quantity will be 0 after removeElement
        }
        return false;
    }

    @Override
    public boolean drinkWater(int quantity) {
        for (ResourceElements waterBank: resources.getValue2()) {
            int actualQuantity = waterBank.getQuantity();
            if (waterBank.removeElement(quantity)) {
                consumedResources.addWater(quantity);
                return true;
            }
            consumedResources.addWater(actualQuantity);
            quantity -= actualQuantity;
        }
        return false;
    }

    @Override
    public void reset() {
        resources.getValue0().clear();
        resources.getValue1().clear();
        resources.getValue2().clear();
        consumedResources.clear();
        expiredResources.clear();
    }
}
