package ca.ulaval.glo4002.game.domain.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PantryTest {
    Pantry pantry;

    @BeforeEach
    void createPantry() {
        pantry = new Pantry();
        pantry.add(new Burger(50));
        pantry.add(new Salad(400));
        pantry.add(new Water(1000));
    }

    @Test
    void whenResourcesAreAdded_canTheyBeFound() {
        assertEquals(50, pantry.findFreshResource().getResourceQuantity(BURGER));
    }

    @Test
    void whenAddingTwoResourceToPantry_shouldIncrementQueue() {
        pantry.add(new Salad(50));
        assertEquals(450, pantry.findFreshResource().getResourceQuantity(SALAD));

    }

    @Test
    void whenDrinkingWater_shouldDecrementFreshQueue() {
        pantry.removeResource(WATER, 100);
        assertEquals(900, pantry.findFreshResource().getResourceQuantity(WATER));
    }

    @Test
    void whenEatingTooMuchBurger_shouldDecrementBurgerQueueToZero() {
        pantry.removeResource(BURGER, 100);
        assertEquals(0, pantry.findFreshResource().getResourceQuantity(BURGER));
    }

    @Test
    void whenEatingSalad_shouldIncrementConsumedResources() {
        pantry.removeResource(SALAD, 70);
        assertEquals(70, pantry.getConsumedResources().getResourceQuantity(SALAD));
    }
}
