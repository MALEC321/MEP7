package ca.ulaval.glo4002.game.domain.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static ca.ulaval.glo4002.game.domain.resources.ResourceTypesEnum.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
       assertEquals(50, pantry.findFreshResource().getResourceQuantity(Burger));
   }

   @Test
   void whenAddingTwoResourceToPantry_shouldIncrementQueue() {
       pantry.add(new Salad(50));
       assertEquals(450, pantry.findFreshResource().getResourceQuantity(Salad));

   }

   @Test
   void whenDrinkingWater_shouldDecrementFreshQueue() {
       pantry.removeResource(Water, 100);
       assertEquals(900, pantry.findFreshResource().getResourceQuantity(Water));
   }

   @Test
   void whenEatingTooMuchBurger_shouldDecrementBurgerQueueToZero() {
       pantry.removeResource(Burger,100);
       assertEquals(0, pantry.findFreshResource().getResourceQuantity(Burger));
   }

   @Test
   void whenEatingSalad_shouldIncrementConsumedResources() {
       pantry.removeResource(Salad, 70);
       assertEquals(70, pantry.getConsumedResources().getResourceQuantity(Salad));
   }
}
