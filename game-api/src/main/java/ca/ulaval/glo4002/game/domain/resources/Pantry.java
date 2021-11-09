package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Pantry implements Eatable {

    private final HashMap<ResourceType, Queue<Resources>> freshResources;
    private final ResourcesGroup consumedResourcesGroup;
    private final ResourcesGroup expiredResourcesGroup;

    public Pantry() {
        Queue<Resources> burgerQueue = new LinkedList<>();
        Queue<Resources> saladQueue = new LinkedList<>();
        Queue<Resources> waterQueue = new LinkedList<>();
        freshResources = new HashMap<>();
        consumedResourcesGroup = new ResourcesGroup();
        expiredResourcesGroup = new ResourcesGroup();

        freshResources.put(BURGER, burgerQueue);
        freshResources.put(SALAD, saladQueue);
        freshResources.put(WATER, waterQueue);
    }

    public ResourcesGroup findFreshResource() {
        ResourcesGroup foundResource = new ResourcesGroup();
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            for (Resources resources : entry.getValue()) {
                foundResource.addResource(entry.getKey(), resources.getQuantity());
            }
        }
        return foundResource;
    }

    public void add(Resources resources) {
        Queue<Resources> burgerQueue = freshResources.get(BURGER);
        Queue<Resources> saladQueue = freshResources.get(SALAD);
        Queue<Resources> waterQueue = freshResources.get(WATER);

        if (resources.getType().equals(BURGER)) {
            burgerQueue.add(resources);
        } else if (resources.getType().equals(SALAD)) {
            saladQueue.add(resources);
        } else if (resources.getType().equals(WATER)) {
            waterQueue.add(resources);
        }
    }

    @Override
    public boolean removeResourceQty(ResourceType typeResource, int quantityParam) {
        int quantity = quantityParam;
        for (Resources resources : freshResources.get(typeResource)) {
            int actualQuantity = resources.getQuantity();

            boolean enoughQuantity = resources.removeElement(quantity);
            if (enoughQuantity) {
                consumedResourcesGroup.addResource(typeResource, quantity);
                return true;
            }

            consumedResourcesGroup.addResource(typeResource, actualQuantity);
            quantity -= actualQuantity;
        }
        return false;
    }

    public void removeAllEmptyResources() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourcesQueue = entry.getValue();
            while (resourcesQueue.peek() != null && resourcesQueue.peek().isEmpty()) {
                resourcesQueue.poll();
            }
        }
    }

    public void removeAllExpiredResources() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourcesQueue =  entry.getValue();
            while (resourcesQueue.peek() != null && resourcesQueue.peek().isExpired()) {
                assert resourcesQueue.peek() != null;
                expiredResourcesGroup.addResource(entry.getKey(), resourcesQueue.peek().getQuantity());
                resourcesQueue.poll();
            }
        }
    }

    public void decreaseExpirationDate() {
        removeAllEmptyResources();
        removeAllExpiredResources();
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            for (Resources resources :  entry.getValue()) {
                resources.decreaseExpirationDate();
            }
        }
    }

    public ResourcesGroup getConsumedResources() {
        return consumedResourcesGroup;
    }

    public ResourcesGroup getExpiredResources() {
        return expiredResourcesGroup;
    }

    public int getFreshResourceQuantity(ResourceType resourceType) {
        return freshResources.get(resourceType).size();
    }

    public void clear() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourcesQueue = entry.getValue();
            resourcesQueue.clear();
        }

        expiredResourcesGroup.clear();
        consumedResourcesGroup.clear();
    }
}
