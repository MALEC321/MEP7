package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourcesStateDtoTest {
    private static final int DTO_STATE_BURGERS_QUANTITY = 10;
    private static final int DTO_STATE_SALADS_QUANTITY = 15;
    private static final int DTO_STATE_WATER_QUANTITY = 20;
    private static final int DTO_STATE_BURGERS_QUANTITY_FROM_PANTRY = 15;
    private static final int DTO_STATE_SALADS_QUANTITY_FROM_PANTRY = 20;
    private static final int DTO_STATE_WATER_QUANTITY_FROM_PANTRY = 25;
    private ResourcesStateDto resourcesStateDto;
    private List<ResourceTypeQuantity> resourceTypeQuantities;
    private ResourcesStateDto resourcesStateDtoFromPantry;
    private List<ResourceTypeQuantity> resourceTypeQuantitiesFromPantry;

    @BeforeEach
    void setUp() {
        setResourcesStateDtoContext();
        setResourcesStateDtoFromPantryContext();
    }

    @Test
    void givenResourcesTypeQuantities_whenCreatingAResourcesStateDto_thenCorrectResourcesTypeQuantitiesAreSet() {

        assertEquals(DTO_STATE_BURGERS_QUANTITY, resourcesStateDto.getQtyForResourceType(BURGER));
        assertEquals(DTO_STATE_SALADS_QUANTITY, resourcesStateDto.getQtyForResourceType(SALAD));
        assertEquals(DTO_STATE_WATER_QUANTITY, resourcesStateDto.getQtyForResourceType(WATER));
    }

    @Test
    void givenTwoResourcesStateDtos_whenAddingTheirResourceTypeQuantitiesOneTotheOther_thenGettingtheSumOfEachResourceTypeQuantities() {

        ResourcesStateDto sumOfresourceTypeQuantities = resourcesStateDto.add(resourcesStateDtoFromPantry);

        assertTrue(DTO_STATE_BURGERS_QUANTITY + DTO_STATE_BURGERS_QUANTITY_FROM_PANTRY == sumOfresourceTypeQuantities.getQtyForResourceType(BURGER));
        assertTrue(DTO_STATE_SALADS_QUANTITY + DTO_STATE_SALADS_QUANTITY_FROM_PANTRY == sumOfresourceTypeQuantities.getQtyForResourceType(SALAD));
        assertTrue(DTO_STATE_WATER_QUANTITY + DTO_STATE_WATER_QUANTITY_FROM_PANTRY == sumOfresourceTypeQuantities.getQtyForResourceType(WATER));
    }

    @Test
    void withdraw() {

    }

    private void setResourcesStateDtoContext() {
        ResourceTypeQuantity burgers = new ResourceTypeQuantity(BURGER, DTO_STATE_BURGERS_QUANTITY);
        ResourceTypeQuantity salads = new ResourceTypeQuantity(SALAD, DTO_STATE_SALADS_QUANTITY);
        ResourceTypeQuantity water = new ResourceTypeQuantity(WATER, DTO_STATE_WATER_QUANTITY);
        resourceTypeQuantities = Arrays.asList(burgers, salads, water);
        resourcesStateDto = new ResourcesStateDto(resourceTypeQuantities);
    }

    private void setResourcesStateDtoFromPantryContext() {
        ResourceTypeQuantity burgers = new ResourceTypeQuantity(BURGER, DTO_STATE_BURGERS_QUANTITY_FROM_PANTRY);
        ResourceTypeQuantity salads = new ResourceTypeQuantity(SALAD, DTO_STATE_SALADS_QUANTITY_FROM_PANTRY);
        ResourceTypeQuantity water = new ResourceTypeQuantity(WATER, DTO_STATE_WATER_QUANTITY_FROM_PANTRY);
        resourceTypeQuantitiesFromPantry = Arrays.asList(burgers, salads, water);
        resourcesStateDtoFromPantry = new ResourcesStateDto(resourceTypeQuantitiesFromPantry);
    }
}