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
        pantry.addResources(resourcesFactory.create(BURGER, 50));
        pantry.addResources(resourcesFactory.create(SALAD, 400));
        pantry.addResources(resourcesFactory.create(WATER, 1000));
    }

    @Test
    void whenResourcesAreAdded_canTheyBeFound() {
        assertEquals(50, pantry.findFreshResources().getResourceQuantity(BURGER));
    }

    @Test
    void whenAddingTwoResourceToPantry_shouldIncrementQueue() {
        pantry.addResources(resourcesFactory.create(SALAD, 50));
        assertEquals(450, pantry.findFreshResources().getResourceQuantity(SALAD));

    }

    @Test
    void whenDrinkingWater_shouldDecrementFreshQueue() {
        pantry.removeResourceQty(WATER, 100);
        assertEquals(900, pantry.findFreshResources().getResourceQuantity(WATER));
    }

    @Test
    void whenEatingTooMuchBurger_shouldDecrementBurgerQueueToZero() {
        pantry.removeResourceQty(BURGER, 100);
        assertEquals(0, pantry.findFreshResources().getResourceQuantity(BURGER));
    }

//    @Test
//    void whenEatingSalad_shouldIncrementConsumedResources() {
//        pantry.removeResourceQty(SALAD, 70);
//        assertEquals(70, pantry.getConsumedResources().getResourceQuantity(SALAD));
//    }
}
