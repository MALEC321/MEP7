package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Pantry implements Eatable {

    private final HashMap<ResourceType, Queue<Resource>> mapResourceQueue;
    private final ResourceGroup consumedResourceGroup;
    private final ResourceGroup expiredResourceGroup;

    public Pantry() {
        Queue<Resource> burgerQueue = new LinkedList<>();
        Queue<Resource> saladQueue = new LinkedList<>();
        Queue<Resource> waterQueue = new LinkedList<>();
        mapResourceQueue = new HashMap<>();
        consumedResourceGroup = new ResourceGroup();
        expiredResourceGroup = new ResourceGroup();

        mapResourceQueue.put(BURGER, burgerQueue);
        mapResourceQueue.put(SALAD, saladQueue);
        mapResourceQueue.put(WATER, waterQueue);
    }

    public ResourceGroup findFreshResource() {
        ResourceGroup foundResource = new ResourceGroup();
        for (Map.Entry<ResourceType, Queue<Resource>> entry : mapResourceQueue.entrySet()) {
            for (Resource resource : entry.getValue()) {
                foundResource.addResource(entry.getKey(), resource.getQuantity());
            }
        }
        return foundResource;
    }

    public void add(Resource resource) {
        Queue<Resource> burgerQueue = mapResourceQueue.get(BURGER);
        Queue<Resource> saladQueue = mapResourceQueue.get(SALAD);
        Queue<Resource> waterQueue = mapResourceQueue.get(WATER);

        if (resource.getResourceType().equals(BURGER)) {
            burgerQueue.add(resource);
        } else if (resource.getResourceType().equals(SALAD)) {
            saladQueue.add(resource);
        } else if (resource.getResourceType().equals(WATER)) {
            waterQueue.add(resource);
        }
    }

    @Override
    public boolean removeResource(ResourceType typeResource, int quantity) {
        for (Resource resource : mapResourceQueue.get(typeResource)) {
            int actualQuantity = resource.getQuantity();

            boolean enoughQuantity = resource.removeElement(quantity);
            if (enoughQuantity) {
                consumedResourceGroup.addResource(typeResource, quantity);
                return true;
            }

            consumedResourceGroup.addResource(typeResource, actualQuantity);
            quantity -= actualQuantity;
        }
        return false;
    }

    public void removeAllEmptyResources() {
        for (Map.Entry<ResourceType, Queue<Resource>> entry : mapResourceQueue.entrySet()) {
            Queue<Resource> resourceQueue = entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isEmpty()) {
                resourceQueue.poll();
            }
        }
    }

    public void removeAllExpiredResources() {
        for (Map.Entry<ResourceType, Queue<Resource>> entry : mapResourceQueue.entrySet()) {
            Queue<Resource> resourceQueue =  entry.getValue();
            while (resourceQueue.peek() != null && resourceQueue.peek().isExpired()) {
                assert resourceQueue.peek() != null;
                expiredResourceGroup.addResource(entry.getKey(), resourceQueue.peek().getQuantity());
                resourceQueue.poll();
            }
        }
    }

    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();
        for (Map.Entry<ResourceType, Queue<Resource>> entry : mapResourceQueue.entrySet()) {
            for (Resource resource :  entry.getValue()) {
                resource.decreaseExpirationDate();
            }
        }
    }

    public ResourceGroup getConsumedResources() {
        return consumedResourceGroup;
    }

    public ResourceGroup getExpiredResources() {
        return expiredResourceGroup;
    }

    public int getFreshResourceQuantity(ResourceType resourceType) {
        return mapResourceQueue.get(resourceType).size();
    }

    public void clear() {
        for (Map.Entry<ResourceType, Queue<Resource>> entry : mapResourceQueue.entrySet()) {
            Queue<Resource> resourceQueue = entry.getValue();
            resourceQueue.clear();
        }

        expiredResourceGroup.clear();
        consumedResourceGroup.clear();
    }
}
