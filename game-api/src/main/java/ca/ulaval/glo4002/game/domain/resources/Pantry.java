package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

@SuppressWarnings("unchecked")
public class Pantry implements Eatable {

    private final HashMap<ResourceType, Object> mapResourceQueue;

    private final Resource consumedResources;
    private final Resource expiredResources;

    public Pantry() {
        Queue<Burger> burgerQueue = new LinkedList<>();
        Queue<Salad> saladQueue = new LinkedList<>();
        Queue<Water> waterQueue = new LinkedList<>();
        mapResourceQueue = new HashMap<>();
        consumedResources = new Resource();
        expiredResources = new Resource();

        mapResourceQueue.put(BURGER, burgerQueue);
        mapResourceQueue.put(SALAD, saladQueue);
        mapResourceQueue.put(WATER, waterQueue);
    }

    public Resource findFreshResource() {
        Resource resource = new Resource();
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            for (ResourceElements resourceElement : (Queue<ResourceElements>) entry.getValue()) {
                resource.addResource(entry.getKey(), resourceElement.getQuantity());
            }
        }
        return resource;
    }

    public void add(ResourceElements resourceElements) {
        Queue<ResourceElements> burgerQueue = (Queue<ResourceElements>) mapResourceQueue.get(BURGER);
        Queue<ResourceElements> saladQueue = (Queue<ResourceElements>) mapResourceQueue.get(SALAD);
        Queue<ResourceElements> waterQueue = (Queue<ResourceElements>) mapResourceQueue.get(WATER);

        if (resourceElements instanceof Burger) {
            burgerQueue.add(resourceElements);
        } else if (resourceElements instanceof Salad) {
            saladQueue.add(resourceElements);
        } else if (resourceElements instanceof Water) {
            waterQueue.add(resourceElements);
        }
    }

    @SuppressWarnings("checkstyle:ParameterAssignment")
    @Override
    public boolean removeResource(ResourceType typeResource, int quantity) {
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
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            Queue<ResourceElements> resourceQueue = (Queue<ResourceElements>) entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isEmpty()) {
                resourceQueue.poll();
            }
        }
    }

    public void removeAllExpiredResources() {
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
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
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            for (ResourceElements resourceElement : (Queue<ResourceElements>) entry.getValue()) {
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

    public int getFreshResourceQuantity(ResourceType resourceType) {
        return ((Queue<ResourceElements>) mapResourceQueue.get(resourceType)).size();
    }

    public void clear() {
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            Queue<ResourceElements> resourceQueue = (Queue<ResourceElements>) entry.getValue();
            resourceQueue.clear();
        }

        expiredResources.clear();
        consumedResources.clear();
    }
}
