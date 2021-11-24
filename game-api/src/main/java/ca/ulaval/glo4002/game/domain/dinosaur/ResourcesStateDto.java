package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.ulaval.glo4002.game.domain.resources.ResourceType;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourcesStateDto {
    private final Map<ResourceType, Integer> resourceTypeQuantities = new HashMap<>();

    public ResourcesStateDto(List<ResourceTypeQuantity> resourceTypeQuantities) {
        for (ResourceTypeQuantity resourceTypeQuantity : resourceTypeQuantities) {
            this.resourceTypeQuantities.put(resourceTypeQuantity.getResourceType(), resourceTypeQuantity.getQuantity());
        }
    }

    public int getQtyForResourceType(ResourceType resourceType) {
        return getResourceTypeQuantities().getOrDefault(resourceType, 0);
    }

    public Map<ResourceType, Integer> getResourceTypeQuantities() {
        return Collections.unmodifiableMap(resourceTypeQuantities);
    }

    public ResourcesStateDto add(ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> resultPantryReport = new ArrayList<>();
        Set<ResourceType> keysOfBothResourceState = new HashSet<>(this.resourceTypeQuantities.keySet());
        keysOfBothResourceState.addAll(resourcesStateDto.getResourceTypeQuantities().keySet());
        for (ResourceType resourceType : keysOfBothResourceState) {
            int resultQuantity = resourcesStateDto.getQtyForResourceType(resourceType);
            resultQuantity += getQtyForResourceType(resourceType);
            resultPantryReport.add(new ResourceTypeQuantity(resourceType, resultQuantity));
        }
        return new ResourcesStateDto(resultPantryReport);
    }

    public ResourcesStateDto createFoodContainerForHerbivore() {
        ResourceTypeQuantity saladQuantity = new ResourceTypeQuantity(SALAD, getQtyForResourceType(SALAD));
        ResourceTypeQuantity waterQuantity = createHalfWaterContainer();
        List<ResourceTypeQuantity> herbivoreFoodContainer = Arrays.asList(saladQuantity, waterQuantity);
        return new ResourcesStateDto(herbivoreFoodContainer);
    }

    public ResourcesStateDto createFoodContainerForCarnivore() {
        ResourceTypeQuantity burgerQuantity = new ResourceTypeQuantity(BURGER, getQtyForResourceType(BURGER));
        ResourceTypeQuantity waterQuantity = createHalfWaterContainer();
        List<ResourceTypeQuantity> carnivoreFoodContainer = Arrays.asList(burgerQuantity, waterQuantity);
        return new ResourcesStateDto(carnivoreFoodContainer);
    }

    private ResourceTypeQuantity createHalfWaterContainer() {
        int waterQuantity = getQtyForResourceType(WATER);
        return new ResourceTypeQuantity(WATER, divideWaterInHalf(waterQuantity));
    }

    private int divideWaterInHalf(int waterQuantity) {
        BigDecimal quantityBd = new BigDecimal(waterQuantity);
        return quantityBd.multiply(new BigDecimal("0.5")).setScale(0, RoundingMode.FLOOR).intValue();
    }
    public ResourcesStateDto removeQuantities(final ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> resourceTypeQuantitiesLeft = new ArrayList<>();
        resourceTypeQuantities.forEach((resourceType, currentResourceQuantity) -> {
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
        return resourcesStateDto.getResourceTypeQuantities().entrySet().stream().noneMatch(entry -> getQtyForResourceType(entry.getKey()) < entry.getValue());
    }
}
