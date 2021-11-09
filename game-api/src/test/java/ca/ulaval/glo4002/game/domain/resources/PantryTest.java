package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PantryTest {
    ResourcesFactory resourcesFactory = new ResourcesFactory();
    Pantry pantry;

    @BeforeEach
    void createPantry() {
        pantry = new Pantry();
        pantry.add(resourcesFactory.create(BURGER, 50));
        pantry.add(resourcesFactory.create(SALAD, 400));
        pantry.add(resourcesFactory.create(WATER, 1000));
    }

    @Test
    void whenResourcesAreAdded_canTheyBeFound() {
        assertEquals(50, pantry.findFreshResource().getResourceQuantity(BURGER));
    }

    @Test
    void whenAddingTwoResourceToPantry_shouldIncrementQueue() {
        pantry.add(resourcesFactory.create(SALAD, 50));
        assertEquals(450, pantry.findFreshResource().getResourceQuantity(SALAD));

    }

    @Test
    void whenDrinkingWater_shouldDecrementFreshQueue() {
        pantry.removeResourceQty(WATER, 100);
        assertEquals(900, pantry.findFreshResource().getResourceQuantity(WATER));
    }

    @Test
    void whenEatingTooMuchBurger_shouldDecrementBurgerQueueToZero() {
        pantry.removeResourceQty(BURGER, 100);
        assertEquals(0, pantry.findFreshResource().getResourceQuantity(BURGER));
    }

    @Test
    void whenEatingSalad_shouldIncrementConsumedResources() {
        pantry.removeResourceQty(SALAD, 70);
        assertEquals(70, pantry.getConsumedResources().getResourceQuantity(SALAD));
    }
}
