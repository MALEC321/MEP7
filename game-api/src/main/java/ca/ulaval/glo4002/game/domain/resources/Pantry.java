package ca.ulaval.glo4002.game.domain.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import ca.ulaval.glo4002.game.domain.dinosaur.ResourceTypeQuantity;
import ca.ulaval.glo4002.game.domain.dinosaur.ResourcesStateDto;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Pantry {
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

    public ResourcesStateDto getFreshResourcesReport() {
        List<ResourceTypeQuantity> resourceTypes = new ArrayList<>(findFreshResources());
        return new ResourcesStateDto(resourceTypes);
    }

    public List<ResourceTypeQuantity> findFreshResources() {
        List<ResourceTypeQuantity> resourceTypeQuantities = new ArrayList<>();
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            ResourceTypeQuantity resourceTypeQuantity = new ResourceTypeQuantity(entry.getKey());
            for (Resources resources : entry.getValue()) {
                resourceTypeQuantity.add(resources.getQuantity());
            }
            resourceTypeQuantities.add(resourceTypeQuantity);
        }
        return resourceTypeQuantities;
    }

    public ResourcesGroup findFreshResourcesGroup() {
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

    public void updateQuantities(ResourcesStateDto updatedResourcesStateDto) {
        ResourcesStateDto actualResourcesStateDto = getFreshResourcesReport();
        actualResourcesStateDto.getResourceTypeQuantities().forEach((resourceType, actualResourceQuantity) -> {
            int resourceQuantityNeeded = updatedResourcesStateDto.getQtyForResourceType(resourceType);
            removeResourceQty(resourceType, resourceQuantityNeeded);
        });
    }

    private void removeResourceQty(ResourceType resourceType, int quantityParam) {
        int quantity = quantityParam;
        for (Resources resources : freshResources.get(resourceType)) {
            int actualQuantity = resources.getQuantity();

            boolean enoughQuantity = resources.removeElement(quantity);
            if (enoughQuantity) {
                consumedResourcesGroup.addResource(resourceType, quantity);
                return;
            }

            consumedResourcesGroup.addResource(resourceType, actualQuantity);
            quantity -= actualQuantity;
        }
    }

    public void removeAllExpiredResources() {
        removeAllEmptyResources();

        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourcesQueue = entry.getValue();
            while (resourcesQueue.peek() != null && resourcesQueue.peek().isExpired()) {
                assert resourcesQueue.peek() != null;
                expiredResourcesGroup.addResource(entry.getKey(), resourcesQueue.peek().getQuantity());
                resourcesQueue.poll();
            }
        }
    }

    public void decreaseExpirationDate() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            for (Resources resources : entry.getValue()) {
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
        return Arrays.asList(findFreshResourcesGroup(), expiredResourcesGroup, consumedResourcesGroup);
    }

    private void removeAllEmptyResources() {
        for (Map.Entry<ResourceType, Queue<Resources>> entry : freshResources.entrySet()) {
            Queue<Resources> resourcesQueue = entry.getValue();
            while (resourcesQueue.peek() != null && resourcesQueue.peek().isEmpty()) {
                resourcesQueue.poll();
            }
        }
    }
}
