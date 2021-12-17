package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DietStrategyFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class DinosaurTest {
    private Dinosaur herbivoreFemale;
    private Dinosaur carnivoreMale;
    private DietStrategyFactory dietStrategyFactory;

    @BeforeEach
    void setUp() {
        dietStrategyFactory = new DietStrategyFactory();
        herbivoreFemale = new Dinosaur("herbivoreFemale", 1000, "f", dietStrategyFactory.create("Brachiosaurus"), "Brachiosaurus");
        carnivoreMale = new Dinosaur("carnivoreMale", 50, "m", dietStrategyFactory.create("Tyrannosaurus Rex"), "Tyrannosaurus Rex");
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateStrength() {
        Dinosaur dinosaur = new Dinosaur("Alaska", 1000, "f", dietStrategyFactory.create("Allosaurus"), "Allosaurus");
        assertEquals(2250, dinosaur.getStrength());
    }

    @Test
    void whenCreatingHerbivoreDinosaur_shouldSetDietToHerbivore() {
        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", dietStrategyFactory.create("Ankylosaurus"), "Ankylosaurus");

        assertEquals(DietType.HERBIVORE, petitpied.getDietType());
    }

    @Test
    void whenCreatingCarnivoreDinosaur_shouldSetDietToCarnivore() {
        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", dietStrategyFactory.create("Megalosaurus"), "Megalosaurus");

        assertEquals(DietType.CARNIVORE, petitpied.getDietType());
    }

    @Test
    void given123kgFemaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new Dinosaur("grosPied", 123, "f", dietStrategyFactory.create("Ankylosaurus"), "Ankylosaurus" );
        int expectedStrength = 185;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgMaleHerbivore_thenStrengthIsEqualTo185() {
        Dinosaur grosPied = new Dinosaur("grosPied", 123, "m", dietStrategyFactory.create("Ankylosaurus"), "Ankylosaurus");
        int expectedStrength = 185;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given123kgFemaleCarnivore_thenStrengthIsEqualTo277() {//12345678
        Dinosaur grosPied = new Dinosaur("grosPied", 123, "f", dietStrategyFactory.create("Velociraptor"), "Velociraptor");
        int expectedStrength = 277;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void given121kgMaleCarnivore_thenStrengthIsEqualTo273() {
        Dinosaur grosPied = new Dinosaur("grosPied", 121, "m", dietStrategyFactory.create("Velociraptor"), "Velociraptor");
        int expectedStrength = 2;

        assertEquals(expectedStrength, grosPied.getStrength());
    }

    @Test
    void givenNewBaby_whenHaveBothParents_thenParentsAreBothAlive() {
        Dinosaur mother = new Dinosaur("mother", 50000, "f", dietStrategyFactory.create("Megalosaurus"), "Megalosaurus");
        Dinosaur father = new Dinosaur("father", 50000, "m", dietStrategyFactory.create("Megalosaurus"), "Megalosaurus");
        Dinosaur baby = new Dinosaur("baby", 20, "f", dietStrategyFactory.create("Megalosaurus"), mother, father, "Megalosaurus");

        assertFalse(baby.isOrphan());
    }
}