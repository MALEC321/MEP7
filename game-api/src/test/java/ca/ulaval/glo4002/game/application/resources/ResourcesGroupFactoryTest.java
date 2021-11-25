package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesGroupFactoryTest {
    private ResourcesGroupFactory resourcesGroupFactory;

    @BeforeEach
    void setUp() {
        resourcesGroupFactory = new ResourcesGroupFactory();
    }

    @Test
    public void foodQuantities_whenCreateResourceGroup_thenReturnResourceGroupWithCorrectQuantities() {
        int qtyBurger = 2;
        int qtySalad = 3;
        int qtyWater = 10;

        ResourcesGroup resourcesGroup = resourcesGroupFactory.create(2, 3, 10);

        assertEquals(qtyBurger, resourcesGroup.getResourceQuantity(BURGER));
        assertEquals(qtySalad, resourcesGroup.getResourceQuantity(SALAD));
        assertEquals(qtyWater, resourcesGroup.getResourceQuantity(WATER));
    }
}