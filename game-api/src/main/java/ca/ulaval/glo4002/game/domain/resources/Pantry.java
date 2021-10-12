package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceTypesEnum.*;

public class Pantry {
    private final Queue<Burger> burgerQueue;
    private final Queue<Salad> saladQueue ;
    private final Queue<Water> waterQueue ;

    private final HashMap<ResourceTypesEnum, Object> mapResourceQueue;

    private final Resource consumedResources;
    private final Resource expiredResources ;

    public Pantry() {
        burgerQueue = new LinkedList<>();
        saladQueue = new LinkedList<>();
        waterQueue = new LinkedList<>();
        mapResourceQueue = new HashMap<>();
        consumedResources = new Resource();
        expiredResources = new Resource();

        mapResourceQueue.put(Burger, burgerQueue);
        mapResourceQueue.put(Salad, saladQueue);
        mapResourceQueue.put(Water, waterQueue);
    }

    public Resource findFreshResource() {
        Resource resource = new Resource();
        for (Map.Entry<ResourceTypesEnum, Object> entry : mapResourceQueue.entrySet()) {
            for (ResourceElements resourceElement : (Queue<ResourceElements>) entry.getValue())
                resource.addResource(entry.getKey(), resourceElement.getQuantity());
        }
        return resource;
    }

    public void add(ResourceElements resourceElements) {
        Queue<ResourceElements> burgerQueue = (Queue<ResourceElements>) mapResourceQueue.get(Burger);
        Queue<ResourceElements> saladQueue = (Queue<ResourceElements>) mapResourceQueue.get(Salad);
        Queue<ResourceElements> waterQueue = (Queue<ResourceElements>) mapResourceQueue.get(Water);

        if (resourceElements instanceof Burger) burgerQueue.add((Burger)resourceElements);
        if (resourceElements instanceof Salad) saladQueue.add((Salad)resourceElements);
        if (resourceElements instanceof Water) waterQueue.add((Water)resourceElements);
    }

    public boolean removeResource(ResourceTypesEnum typeResource, int quantity) {
        for (ResourceElements resourceElement : (Queue<ResourceElements>) mapResourceQueue.get(typeResource)) {
            int actualQuantity = resourceElement.getQuantity();

            boolean enoughQuantity = resourceElement.removeElement(quantity);
            if (enoughQuantity) {
                consumedResources.addResource(typeResource, quantity);
                return true;
            }

            consumedResources.addResource(typeResource, actualQuantity);
            quantity -= actualQuantity;
        }
    return false;
    }

    public void removeAllEmptyResources() {
        for (Map.Entry<ResourceTypesEnum, Object> entry : mapResourceQueue.entrySet()) {
            Queue<ResourceElements> resourceQueue = (Queue<ResourceElements>) entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isEmpty()) {
                resourceQueue.poll();
            }
        }
    }

    public void removeAllExpiredResources() {
        for (Map.Entry<ResourceTypesEnum, Object> entry : mapResourceQueue.entrySet()) {
            Queue<ResourceElements> resourceQueue = (Queue<ResourceElements>) entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isExpired()) {
                assert resourceQueue.peek() != null;
                expiredResources.addResource(entry.getKey(), resourceQueue.peek().getQuantity());
                resourceQueue.poll();
            }
        }
    }

    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();
        for (Map.Entry<ResourceTypesEnum, Object> entry : mapResourceQueue.entrySet()) {
            for (ResourceElements resourceElement: (Queue<ResourceElements>) entry.getValue()) {
                resourceElement.decreaseExpirationDate();
            }
        }
    }

    public Resource getConsumedResources() {
        return consumedResources;
    }

    public Resource getExpiredResources() {
        return expiredResources;
    }

    public void clear() {
        for (Map.Entry<ResourceTypesEnum, Object> entry : mapResourceQueue.entrySet()) {
            Queue<ResourceElements> resourceQueue = (Queue<ResourceElements>) entry.getValue();
            resourceQueue.clear();
        }

        expiredResources.clear();
        consumedResources.clear();
    }
}