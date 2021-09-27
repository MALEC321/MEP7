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
    public void removeStale() {
        resourceInventory.remove();
    }

    @Override
    public Queue<ResourceElements> findAll(){
        return resourceInventory;
    }

    @Override
    public boolean consume(ResourceElements resourceElement, int quantity) {
        if (resourceElement instanceof Burger) {
            return eatBurger(resources.getValue0(), quantity);
        }  else if (resourceElement instanceof Salad) {
            return eatSalad(resources.getValue1(), quantity);
        }  else if (resourceElement instanceof Water) {
            return drinkWater(resources.getValue2(), quantity);
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

    private boolean eatBurger(Queue<Burger> BurgerBank, int quantity) {
        for (ResourceElements burgerBowl: BurgerBank) {
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
    private boolean eatSalad(Queue<Salad> saladBank, int quantity) {
        for (ResourceElements saladBowl: saladBank) {
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
    private boolean drinkWater(Queue<Water> waterBanks, int quantity) {
        for (ResourceElements waterBank: waterBanks) {
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
