package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

@SuppressWarnings("unchecked")
public class Pantry implements Eatable {

    private final HashMap<ResourceType, Object> mapResourceQueue;

    private final Resources consumedResources;
    private final Resources expiredResources;

    public Pantry() {
        Queue<Burger> burgerQueue = new LinkedList<>();
        Queue<Salad> saladQueue = new LinkedList<>();
        Queue<Water> waterQueue = new LinkedList<>();
        mapResourceQueue = new HashMap<>();
        consumedResources = new Resources();
        expiredResources = new Resources();

        mapResourceQueue.put(BURGER, burgerQueue);
        mapResourceQueue.put(SALAD, saladQueue);
        mapResourceQueue.put(WATER, waterQueue);
    }

    public Resources findFreshResource() {
        Resources resource = new Resources();
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            for (Resource resourceElement : (Queue<Resource>) entry.getValue()) {
                resource.addResource(entry.getKey(), resourceElement.getQuantity());
            }
        }
        return resource;
    }

    public void add(Resource resource) {
        Queue<Resource> burgerQueue = (Queue<Resource>) mapResourceQueue.get(BURGER);
        Queue<Resource> saladQueue = (Queue<Resource>) mapResourceQueue.get(SALAD);
        Queue<Resource> waterQueue = (Queue<Resource>) mapResourceQueue.get(WATER);

        if (resource instanceof Burger) {
            burgerQueue.add(resource);
        } else if (resource instanceof Salad) {
            saladQueue.add(resource);
        } else if (resource instanceof Water) {
            waterQueue.add(resource);
        }
    }

    @SuppressWarnings("checkstyle:ParameterAssignment")
    @Override
    public boolean removeResource(ResourceType typeResource, int quantity) {
        for (Resource resourceElement : (Queue<Resource>) mapResourceQueue.get(typeResource)) {
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
            Queue<Resource> resourceQueue = (Queue<Resource>) entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isEmpty()) {
                resourceQueue.poll();
            }
        }
    }

    public void removeAllExpiredResources() {
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            Queue<Resource> resourceQueue = (Queue<Resource>) entry.getValue();
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
            for (Resource resourceElement : (Queue<Resource>) entry.getValue()) {
                resourceElement.decreaseExpirationDate();
            }
        }
    }

    public Resources getConsumedResources() {
        return consumedResources;
    }

    public Resources getExpiredResources() {
        return expiredResources;
    }

    public int getFreshResourceQuantity(ResourceType resourceType) {
        return ((Queue<Resource>) mapResourceQueue.get(resourceType)).size();
    }

    public void clear() {
        for (Map.Entry<ResourceType, Object> entry : mapResourceQueue.entrySet()) {
            Queue<Resource> resourceQueue = (Queue<Resource>) entry.getValue();
            resourceQueue.clear();
        }

        expiredResources.clear();
        consumedResources.clear();
    }
}
