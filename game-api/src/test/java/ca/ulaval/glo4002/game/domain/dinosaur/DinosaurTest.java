package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurTest {

    private Dinosaur herbivoreFemale;
    private Dinosaur carnivoreMale;

    @BeforeEach
    void setUp() {
        herbivoreFemale = new DinosaurAdult("herbivoreFemale", 1000, "f", "Brachiosaurus");
        carnivoreMale = new DinosaurAdult("carnivoreMale", 50, "m", "Tyrannosaurus Rex");
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateStrength() {
        Dinosaur dinosaur = new DinosaurAdult("Alaska", 1000, "f", "Allosaurus");
        assertEquals(2250, dinosaur.getStrength());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateWaterNeeded() {
        Dinosaur dinosaur = new DinosaurAdult("Will", 1000, "f", "Allosaurus");
        assertEquals(1200, dinosaur.feedWater());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateFoodNeeded() {
        Dinosaur dinosaur = new DinosaurAdult("Cherry", 1000, "f", "Allosaurus");
        assertEquals(2, dinosaur.feedFood());
    }

    @Test
    void whenCreatingHerbivoreDinosaur_shouldSetDietToHerbivore() {
        Dinosaur petitpied = new DinosaurAdult("petitpied", 1000, "f", "Ankylosaurus");

        assertEquals(DietType.HERBIVORE, petitpied.getDiet());
    }

    @Test
    void whenCreatingCarnivoreDinosaur_shouldSetDietToCarnivore() {
        Dinosaur petitpied = new DinosaurAdult("petitpied", 1000, "f", "Megalosaurus");

        assertEquals(DietType.CARNIVORE, petitpied.getDiet());
    }

    @Test
    void givenNewlyAddedDinosaur_waterNeedShouldBeDoubled() {
        int waterNeeded = 1200;

        assertEquals(waterNeeded, herbivoreFemale.getWaterNeed());
    }

    @Test
    void given123kgFemaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 2, "f", "Ankylosaurus");
        int expectedStrength = 3;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgMaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 1, "m", "Ankylosaurus");
        int expectedStrength = 1;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgFemaleCarnivore_thenStrengthIsEqualTo277() {//12345678
        Dinosaur grosPied = new DinosaurAdult("grosPied", 1, "f", "Velociraptor");
        int expectedStrength = 3;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given121kgMaleCarnivore_thenStrengthIsEqualTo273() {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 1, "m", "Velociraptor");
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

        assertEquals(expectedFoodNeeds, carnivoreMale.feedFood());
    }

    @Test
    void givenNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsIsDoubled() {
        int expectedFoodNeeds = 5;

        assertEquals(expectedFoodNeeds, herbivoreFemale.feedFood());
    }

    @Test
    void givenNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals100()  {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 50001, "f", "Velociraptor");
        int expectedFoodNeeds = 101;

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals50()  {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 50001, "f", "Velociraptor");
        int expectedFoodNeeds = 51;
        grosPied.setNewlyAdded(false);

        assertEquals(expectedFoodNeeds, grosPied.getFoodNeed());
    }

    @Test
    void givenNotNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsEquals3() {
        int expectedFoodNeeds = 5;
        herbivoreFemale.feedFood();

        assertEquals(expectedFoodNeeds, herbivoreFemale.feedFood());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals100() {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 50000, "f", "Megalosaurus");
        int expectedFoodNeeds = 100;

        assertEquals(expectedFoodNeeds, grosPied.feedFood());
    }

    @Test
    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals50() {
        Dinosaur grosPied = new DinosaurAdult("grosPied", 50000, "f", "Megalosaurus");
        int expectedFoodNeeds = 100;
        grosPied.feedFood();

        assertEquals(expectedFoodNeeds, grosPied.feedFood());
    }
}