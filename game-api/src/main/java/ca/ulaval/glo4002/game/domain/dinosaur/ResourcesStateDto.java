package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.resources.ResourceType;

import java.math.BigDecimal;
import java.util.*;

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

    private Set<ResourceType> addedMissingResourcesType(ResourcesStateDto resourcesStateDto) {
        Set<ResourceType> keysOfBothResourceState = new HashSet<>(getResourceTypeQuantities().keySet());
        keysOfBothResourceState.addAll(resourcesStateDto.getResourceTypeQuantities().keySet());
        return keysOfBothResourceState;
    }

    public ResourcesStateDto add(ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> bothPantryReportUnified = new ArrayList<>();
        Set<ResourceType> bothResourceTypes = addedMissingResourcesType(resourcesStateDto);
        for (ResourceType resourceType : bothResourceTypes) {
            int resultQuantity = resourcesStateDto.getQtyForResourceType(resourceType);
            resultQuantity += getQtyForResourceType(resourceType);
            bothPantryReportUnified.add(new ResourceTypeQuantity(resourceType, resultQuantity));
        }
        return new ResourcesStateDto(bothPantryReportUnified);
    }

    public ResourcesStateDto minus(ResourcesStateDto resourcesStateDto) {
        List<ResourceTypeQuantity> bothPantryReportUnified = new ArrayList<>();
        Set<ResourceType> bothResourceTypes = addedMissingResourcesType(resourcesStateDto);
        for (ResourceType resourceType : bothResourceTypes) {
            int resultQuantity = getQtyForResourceType(resourceType);
            resultQuantity -= resourcesStateDto.getQtyForResourceType(resourceType);
            bothPantryReportUnified.add(new ResourceTypeQuantity(resourceType, resultQuantity));
        }
        return new ResourcesStateDto(bothPantryReportUnified);
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
        return (int) Math.floor(quantityBd.multiply(new BigDecimal("0.5")).doubleValue());
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
