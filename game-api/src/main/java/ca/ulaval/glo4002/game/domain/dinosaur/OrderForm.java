package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ca.ulaval.glo4002.game.application.exceptions.food.NoFoodsLeftException;
import ca.ulaval.glo4002.game.domain.resources.ResourceType;

public class OrderForm { // TODO change to value object
    private final Map<ResourceType, Integer> foodNeeds = new HashMap<>();

    public OrderForm(ResourceTypeQuantity... resourcesAndQuantities) {
        for (ResourceTypeQuantity resourceTypeQuantity : resourcesAndQuantities) {
            foodNeeds.put(resourceTypeQuantity.getResourceType(), resourceTypeQuantity.getQuantity());
        }
    }

    public void incrementFood(ResourceType resourceType, Integer qty) { // TOdo change to find a domain name
                                                                        // Todo check Pattern builder Pantry report as name
        if (!foodNeeds.containsKey(resourceType)) {
            foodNeeds.put(resourceType, qty);
        } else {
            Integer currentQty = foodNeeds.get(resourceType);
            currentQty += qty;
            foodNeeds.put(resourceType, currentQty);
        }
    }

    public void addFoods(ResourceTypeQuantity... resourcesAndQuantities) {
        for (ResourceTypeQuantity resourceTypeQuantity : resourcesAndQuantities) {
            incrementFood(resourceTypeQuantity.getResourceType(), resourceTypeQuantity.getQuantity());
        }
    }

    public Collection<ResourceType> getResourcesTypes() {
        return foodNeeds.keySet();
    }

    public Integer getQtyForResourceType(ResourceType resourceType) {
        return foodNeeds.get(resourceType);
    }

    private void removeQuantityForResourceType(ResourceType resourceType, int quantity) {
        int resourceQuantity = getQtyForResourceType(resourceType);
        resourceQuantity -= quantity;
        foodNeeds.replace(resourceType, resourceQuantity);
    }

    public void removeQuantityForResource(ResourceType resourceType, int resourceQuantity) {
        removeQuantityForResourceType(resourceType, resourceQuantity);
        if (getQtyForResourceType(resourceType) < 0) {
            throw new NoFoodsLeftException();
        }
    }
}
