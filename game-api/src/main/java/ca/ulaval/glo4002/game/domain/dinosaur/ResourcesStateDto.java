package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

import ca.ulaval.glo4002.game.domain.resources.ResourceType;

public class ResourcesStateDto {
    private final Map<ResourceType, Integer> pantryQuantities = new HashMap<>();

    public ResourcesStateDto(List<ResourceTypeQuantity> resourceTypeQuantities) {
        for (ResourceTypeQuantity resourceTypeQuantity : resourceTypeQuantities) {
            pantryQuantities.put(resourceTypeQuantity.getResourceType(), resourceTypeQuantity.getQuantity());
        }
    }

    public int getQtyForResourceType(ResourceType resourceType) {
        return pantryQuantities.getOrDefault(resourceType, 0);
    }

    public Map<ResourceType, Integer> getPantryQuantities() {
        return pantryQuantities;
    }

    public ResourcesStateDto add(ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> resultPantryReport = new ArrayList<>();
        Set<ResourceType> keysOfBothResourceState = new HashSet<>(this.pantryQuantities.keySet());
        keysOfBothResourceState.addAll(resourcesStateDto.getPantryQuantities().keySet());
        for (ResourceType resourceType : keysOfBothResourceState) {
            int resultQuantity = resourcesStateDto.getQtyForResourceType(resourceType);
            resultQuantity += getQtyForResourceType(resourceType);
            resultPantryReport.add(new ResourceTypeQuantity(resourceType, resultQuantity));
        }
        return new ResourcesStateDto(resultPantryReport);
    }

    public ResourcesStateDto withdraw(ResourceType resourceTypeWanted) {
        List<ResourceTypeQuantity> resultResourcesState = new ArrayList<>();
        this.pantryQuantities.forEach((resourceType, quantity) -> {
            if (resourceType == WATER) {
                BigDecimal quantityBd = new BigDecimal(quantity);
                int halfQuantity = quantityBd.multiply(new BigDecimal("0.5")).setScale(0, RoundingMode.FLOOR).intValue();
                resultResourcesState.add(new ResourceTypeQuantity(resourceType, halfQuantity));
            } else {
                resultResourcesState.add(new ResourceTypeQuantity(resourceType, quantity));
            }
        });
        ResourceType unWantedResourceTypeWanted = (resourceTypeWanted == BURGER) ? SALAD : BURGER;
        resultResourcesState.removeIf(resourceTypeQuantity -> resourceTypeQuantity.getResourceType() == unWantedResourceTypeWanted);
        return new ResourcesStateDto(resultResourcesState);
    }

    public ResourcesStateDto removeQuantities(final ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> resourceTypeQuantitiesLeft = new ArrayList<>();
        pantryQuantities.forEach((resourceType, currentResourceQuantity) -> {
            int resourceQuantityNeeded = resourcesStateDto.getQtyForResourceType(resourceType);
            int resourceQuantityLeft = 0;
            if (currentResourceQuantity >= resourceQuantityNeeded) {
                resourceQuantityLeft = currentResourceQuantity - resourceQuantityNeeded;
            }
            resourceTypeQuantitiesLeft.add(new ResourceTypeQuantity(resourceType, resourceQuantityLeft));
        });
        return new ResourcesStateDto(resourceTypeQuantitiesLeft);
    }

    public boolean checkIfThereIsEnoughQuantity(final ResourcesStateDto resourcesStateDto) {
        return resourcesStateDto.getPantryQuantities().entrySet().stream().noneMatch(entry -> getQtyForResourceType(entry.getKey()) < entry.getValue());
    }
}
