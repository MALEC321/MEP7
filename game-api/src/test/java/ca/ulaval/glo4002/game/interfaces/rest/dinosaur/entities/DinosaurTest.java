package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    void given123kgFemaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new Dinosaur("grosPied", 2, "f", "Ankylosaurus");
        int expectedStrength = 3;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgMaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new Dinosaur("grosPied", 1, "m", "Ankylosaurus");
        int expectedStrength = 1;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgFemaleCarnivore_thenStrengthIsEqualTo277() {//12345678
        Dinosaur grosPied = new Dinosaur("grosPied", 1, "f", "Velociraptor");
        int expectedStrength = 3;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given121kgMaleCarnivore_thenStrengthIsEqualTo273() {
        Dinosaur grosPied = new Dinosaur("grosPied", 2, "m", "Velociraptor");
        int expectedStrength = 3;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given50kgMaleCarnivore_thenStrengthIsEqualTo75() {
        int expectedStrength = 75;

        assertEquals(expectedStrength, carnivoreMale.getStrength());
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
    void givenNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals100()  {
        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Velociraptor");
        int expectedFoodNeeds = 100;

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals50()  {
        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Velociraptor");
        int expectedFoodNeeds = 50;
        grosPied.setNewlyAdded(false);

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsEquals3() {
        int expectedFoodNeeds = 3;
        herbivoreFemale.setNewlyAdded(false);

        assertEquals(expectedFoodNeeds, herbivoreFemale.getFoodNeed());
    }
}
