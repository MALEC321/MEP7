package ca.ulaval.glo4002.game.domain.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PantryTest {
    Pantry pantry;

    @BeforeEach
    void createPantry () {
        pantry = new Pantry();
        pantry.add(new Burger(50));
        pantry.add(new Salad(400));
        pantry.add(new Water(1000));
    }

    @Test
    void whenResourcesAreAdded_canTheyBeFound() {
        assertEquals(50, pantry.findFreshResource().getBurgersQuantity());
    }

    @Test
    void whenAddingTwoResourceToPantry_shouldIncrementQueue() {
        pantry.add(new Salad(50));
        assertEquals(450, pantry.findFreshResource().getSaladQuantity());

    }

    @Test
    void whenDrinkingWater_shouldDecrementFreshQueue() {
        pantry.removeWater(100);
        assertEquals(900, pantry.findFreshResource().getWaterQuantity());
    }

    @Test
    void whenEatingTooMuchBurger_shouldDecrementBurgerQueueToZero() {
        pantry.removeBurgers(100);
        assertEquals(0, pantry.findFreshResource().getBurgersQuantity());
    }

    @Test
    void whenEatingSalad_shouldIncrementConsumedResources() {
        pantry.removeSalads(70);
        assertEquals(70, pantry.getConsumedResources().getSaladQuantity());
    }
}
