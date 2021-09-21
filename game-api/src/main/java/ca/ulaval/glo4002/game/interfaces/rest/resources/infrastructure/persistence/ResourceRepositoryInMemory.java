package ca.ulaval.glo4002.game.interfaces.rest.resources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Action;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.*;
import javassist.compiler.ast.Pair;
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

    @Override
    public boolean eat(ResourceElements resourceElement, int quantity) {
        if (resourceElement instanceof Burger&& resources.getValue0().peek() != null) {
            return eatSomething(resources.getValue0(), quantity);
        }  else if (resourceElement instanceof Salad && resources.getValue1().peek() != null) {
            return eatSomething(resources.getValue1(), quantity);
        }  else if (resourceElement instanceof Water && resources.getValue2().peek() != null) {
            return eatSomething(resources.getValue2(), quantity);
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

    private boolean eatSomething(Queue<ResourceElements> resourceElements, int quantity) {
        for (ResourceElements resourceElement: resourceElements) {
            if (resourceElement.removeElement(quantity)) {
                return true;
            }
            quantity -= resourceElement.getQuantity();
        }
        return false;
    }

}