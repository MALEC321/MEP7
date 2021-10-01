package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinosaurTest {

    private Dinosaur herbivoreFemale;
    private Dinosaur carnivoreMale;

    @BeforeEach
    void setUp() {
        herbivoreFemale = new Dinosaur("herbivoreFemale", 1000, "f", "Brachiosaurus");
        carnivoreMale = new Dinosaur("carnivoreMale", 50, "m", "Tyrannosaurus Rex");
    }

    @Test
    void whenCreatingHerbivoreDinosaur_shouldSetDietToHerbivore() {
        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", "Ankylosaurus");

        assertEquals(DietType.HERBIVORE, petitpied.getDiet());
    }

    @Test
    void whenCreatingCarnivoreDinosaur_shouldSetDietToCarnivore() {
        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", "Megalosaurus");

        assertEquals(DietType.CARNIVORE, petitpied.getDiet());
    }

    @Test
    void givenNewlyAddedDinosaur_waterNeedShouldBeDoubled() {
        int waterNeeded = 1200;

        assertEquals(waterNeeded, herbivoreFemale.getWaterNeed());
    }

    @Test
    void given1000kgFemaleHerbivore_thenForceIsEqualTo1500() {
        int expectedForce = 1500;

        assertEquals(expectedForce, herbivoreFemale.getForce());
    }

    @Test
    void given50kgMaleCarnivore_thenForceIsEqualTo75() {
        int expectedForce = 75;

        assertEquals(expectedForce, carnivoreMale.getForce());
    }

    @Test
    void givenNewlyAdded50kgMaleCarnivore_thenFoodNeedsEquals1() {
        int expectedFoodNeeds = 1;

        assertEquals(expectedFoodNeeds, carnivoreMale.getFoodNeed());
    }

    @Test
    void givenNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsIsDoubled() {
        int expectedFoodNeeds = 5;

        assertEquals(expectedFoodNeeds, herbivoreFemale.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsEquals3() {
        int expectedFoodNeeds = 3;
        herbivoreFemale.setNewlyAdded(false);

        assertEquals(expectedFoodNeeds, herbivoreFemale.getFoodNeed());
    }
}
