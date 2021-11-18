package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.domain.dinosaur.OrderForm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Pantry implements FoodContainer {
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

    public ResourcesGroup findFreshResources() {
        ResourcesGroup foundResource = new ResourcesGroup();
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            for (Resources resources : entry.getValue()) {
                foundResource.addResource(entry.getKey(), resources.getQuantity());
            }
        }
        return foundResource;
    }

    public void addResources(Resources resources) {
        freshResources.get(resources.getType()).add(resources);
    }

    public void removeQuantity(OrderForm orderForm) {
        int burgersQuantity = findFreshResources().getResourceQuantity(BURGER);
        int saladsQuantity = findFreshResources().getResourceQuantity(SALAD);
        int waterQuantity = findFreshResources().getResourceQuantity(WATER);
        removeResourceQty(BURGER, orderForm.getQtyForResourceType(BURGER) <= 0 ? burgersQuantity : burgersQuantity - orderForm.getQtyForResourceType(BURGER));
        removeResourceQty(SALAD, orderForm.getQtyForResourceType(SALAD) <= 0 ? saladsQuantity : saladsQuantity - orderForm.getQtyForResourceType(SALAD));
        removeResourceQty(WATER, orderForm.getQtyForResourceType(WATER) <= 0 ? waterQuantity : waterQuantity - orderForm.getQtyForResourceType(WATER));
    }

    @Override
    public boolean removeResourceQty(ResourceType resourceType, int quantityParam) {
        int quantity = quantityParam;
        for (Resources resources : freshResources.get(resourceType)) {
            int actualQuantity = resources.getQuantity();

            boolean enoughQuantity = resources.removeElement(quantity);
            if (enoughQuantity) {
                consumedResourcesGroup.addResource(resourceType, quantity);
                return true;
            }

            consumedResourcesGroup.addResource(resourceType, actualQuantity);
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

    public void clear() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourceQueue = entry.getValue();
            resourceQueue.clear();
        }

        consumedResourcesGroup.clear();
        expiredResourcesGroup.clear();
    }

    public List<ResourcesGroup> findAll() {
        return Arrays.asList(findFreshResources(), expiredResourcesGroup, consumedResourcesGroup);
    }
}
