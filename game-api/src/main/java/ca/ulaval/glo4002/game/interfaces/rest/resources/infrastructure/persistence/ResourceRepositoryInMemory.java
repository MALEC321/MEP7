package ca.ulaval.glo4002.game.interfaces.rest.resources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.*;
import org.javatuples.Triplet;

import java.util.*;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private final Queue<ResourceElements> resourceInventory = new LinkedList<>();
    Triplet<Queue<Burger>, Queue<Salad>, Queue<Water>> resources;
    private final Resource consumedResources = new Resource(new Burger(0), new Salad(0), new Water(0));
    private final Resource expiredResources = new Resource(new Burger(0), new Salad(0), new Water(0));

    @Override
    public void add(ResourceElements resourceElement) {
        if (resourceElement instanceof Burger) resources.getValue0().add((Burger)resourceElement);
        if (resourceElement instanceof Salad) resources.getValue1().add((Salad)resourceElement);
        if (resourceElement instanceof Water) resources.getValue2().add((Water)resourceElement);
    }

    @Override
    public void removeStale() {
        resourceInventory.remove();
    }

    @Override
    public Queue<ResourceElements> findAll(){
        return resourceInventory;
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

    @Override
    public boolean eatBurger(int quantity) {
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

    @Override
    public boolean eatSalad(int quantity) {
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

    @Override
    public boolean drinkWater(int quantity) {
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