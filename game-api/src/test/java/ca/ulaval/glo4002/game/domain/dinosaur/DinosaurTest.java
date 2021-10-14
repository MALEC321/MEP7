package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

class DinosaurTest {

    private Dinosaur herbivoreFemale;
    private Dinosaur carnivoreMale;

    @BeforeEach
    void setUp() {
        herbivoreFemale = new Dinosaur("herbivoreFemale", 1000, "f", "Brachiosaurus");
        carnivoreMale = new Dinosaur("carnivoreMale", 50, "m", "Tyrannosaurus Rex");
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateStrength() {
        Dinosaur dinosaur = new Dinosaur("Alaska", 1000, "f", "Allosaurus");
        assertEquals(2250, dinosaur.getStrength());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateWaterNeeded() {
        Dinosaur dinosaur = new Dinosaur("Will", 1000, "f", "Allosaurus");
        assertEquals(1200, dinosaur.getWaterQuantityNeeded());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateFoodNeeded() {
        Dinosaur dinosaur = new Dinosaur("Cherry", 1000, "f", "Allosaurus");
        assertEquals(2, dinosaur.getFoodQuantityNeeded());
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
        Dinosaur grosPied = new Dinosaur("grosPied", 1, "m", "Velociraptor");
        int expectedStrength = 2;

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

        assertEquals(expectedFoodNeeds, carnivoreMale.getFoodQuantityNeeded());
    }

    @Test
    void givenNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsIsDoubled() {
        int expectedFoodNeeds = 5;

        assertEquals(expectedFoodNeeds, herbivoreFemale.getFoodQuantityNeeded());
    }

    @Test
    void givenNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals100() {
        Dinosaur grosPied = new Dinosaur("grosPied", 50001, "f", "Velociraptor");
        int expectedFoodNeeds = 101;

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals50() {
        Dinosaur grosPied = new Dinosaur("grosPied", 50001, "f", "Velociraptor");
        int expectedFoodNeeds = 51;
        grosPied.setNewlyAdded(false);

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsEquals3() {
        int expectedFoodNeeds = 5;
        herbivoreFemale.getFoodQuantityNeeded();

        assertEquals(expectedFoodNeeds, herbivoreFemale.getFoodQuantityNeeded());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals100() {
        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Megalosaurus");
        int expectedFoodNeeds = 100;

        assertEquals(expectedFoodNeeds, grosPied.getFoodQuantityNeeded());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals50() {
        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Megalosaurus");
        int expectedFoodNeeds = 100;
        grosPied.getFoodQuantityNeeded();

        assertEquals(expectedFoodNeeds, grosPied.getFoodQuantityNeeded());
    }
}