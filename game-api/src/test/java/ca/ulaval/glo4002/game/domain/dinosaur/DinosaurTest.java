package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DietStrategyFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

//    @Test
//    void whenCreatingDinosaur_shouldCalculateWaterNeeded() {
//        Dinosaur dinosaur = new Dinosaur("Will", 1000, "f", "Allosaurus", );
//        assertEquals(1200, dinosaur.calculateWaterNeeds());
//    }
//
//    @Test
//    void whenCreatingDinosaur_shouldCalculateFoodNeeded() {
//        Dinosaur dinosaur = new Dinosaur("Cherry", 1000, "f", "Allosaurus", );
//        assertEquals(2, dinosaur.calculateBurgersNeeds());
//    }
//
//    @Test
//    void whenCreatingHerbivoreDinosaur_shouldSetDietToHerbivore() {
//        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", "Ankylosaurus", );
//
//        assertEquals(DietType.HERBIVORE, petitpied.getDiet());
//    }
//
//    @Test
//    void whenCreatingCarnivoreDinosaur_shouldSetDietToCarnivore() {
//        Dinosaur petitpied = new Dinosaur("petitpied", 1000, "f", "Megalosaurus", );
//
//        assertEquals(DietType.CARNIVORE, petitpied.getDiet());
//    }
//
//    @Test
//    void givenNewlyAddedDinosaur_waterNeedShouldBeDoubled() {
//        int waterNeeded = 1200;
//
//        assertEquals(waterNeeded, herbivoreFemale.calculateWaterNeeds());
//    }
//
//    @Disabled("Disabled until dino refactor is done!")
//    @Test
//    void given123kgFemaleHerbivore_thenStrengthIsEqualTo185() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 2, "f", "Ankylosaurus", );
//        int expectedStrength = 3;
//
//        assertEquals(expectedStrength, grosPied.getStrength());
//    }
//
//    @Disabled("Disabled until dino refactor is done!")
//    @Test
//    void given123kgMaleHerbivore_thenStrengthIsEqualTo185() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 1, "m", "Ankylosaurus", );
//        int expectedStrength = 1;
//
//        assertEquals(expectedStrength, grosPied.getStrength());
//    }
//
//    @Test
//    void given123kgFemaleCarnivore_thenStrengthIsEqualTo277() {//12345678
//        Dinosaur grosPied = new Dinosaur("grosPied", 1, "f", "Velociraptor", );
//        int expectedStrength = 3;
//
//        assertEquals(expectedStrength, grosPied.getStrength());
//    }
//
//    @Test
//    void given121kgMaleCarnivore_thenStrengthIsEqualTo273() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 1, "m", "Velociraptor", );
//        int expectedStrength = 2;
//
//        assertEquals(expectedStrength, grosPied.getStrength());
//    }
//
//    @Test
//    void given50kgMaleCarnivore_thenStrengthIsEqualTo75() {
//        int expectedStrength = 75;
//
//        assertEquals(expectedStrength, carnivoreMale.getStrength());
//    }
//
//    @Test
//    void givenNewlyAdded50kgMaleCarnivore_thenFoodNeedsEquals1() {
//        int expectedFoodNeeds = 1;
//
//        assertEquals(expectedFoodNeeds, carnivoreMale.calculateBurgersNeeds());
//    }
//
//    @Disabled("Disabled until dino refactor is done!")
//    @Test
//    void givenNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsIsDoubled() {
//        int expectedFoodNeeds = 5;
//
//        assertEquals(expectedFoodNeeds, herbivoreFemale.calculateBurgersNeeds());
//    }
//
//    @Test
//    void givenNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals100() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 50001, "f", "Velociraptor", );
//        int expectedFoodNeeds = 101;
//
//        assertEquals(expectedFoodNeeds, grosPied.calculateBurgersNeeds());
//    }
//
//    @Test
//    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsEquals50() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 50001, "f", "Velociraptor", );
//        int expectedFoodNeeds = 51;
//        grosPied.setHungry(false);
//
//        assertEquals(expectedFoodNeeds, grosPied.calculateBurgersNeeds());
//    }
//
//    @Disabled("Disabled until dino refactor is done!")
//    @Test
//    void givenNotNewlyAdded1000kgFemaleHerbivore_thenFoodNeedsEquals3() {
//        int expectedFoodNeeds = 5;
//        herbivoreFemale.calculateBurgersNeeds();
//
//        assertEquals(expectedFoodNeeds, herbivoreFemale.calculateBurgersNeeds());
//    }
//
//    @Test
//    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals100() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Megalosaurus", );
//        int expectedFoodNeeds = 100;
//
//        assertEquals(expectedFoodNeeds, grosPied.calculateBurgersNeeds());
//    }
//
//    @Test
//    void givenNotNewlyAdded50000kgFemaleCarnivore_thenFoodNeedsDoubledEquals50() {
//        Dinosaur grosPied = new Dinosaur("grosPied", 50000, "f", "Megalosaurus", );
//        int expectedFoodNeeds = 100;
//        grosPied.calculateBurgersNeeds();
//
//        assertEquals(expectedFoodNeeds, grosPied.calculateBurgersNeeds());
//    }

    @Test
    void givenNewBaby_whenHaveBothParents_thenParentsAreBothAlive() {
        Dinosaur mother = new Dinosaur("mother", 50000, "f", dietStrategyFactory.create("Megalosaurus"), "Megalosaurus");
        Dinosaur father = new Dinosaur("father", 50000, "m", dietStrategyFactory.create("Megalosaurus"), "Megalosaurus");
        Dinosaur baby = new Dinosaur("baby", 20, "f", dietStrategyFactory.create("Megalosaurus"), mother, father, "Megalosaurus");

        assertFalse(baby.areBothParentsDead());
    }

//    @Test
//    void givenBaby_whenOneParentDied_thenParentsAreNotBothAlive() {
//        Dinosaur deadMother = new Dinosaur("deadMother", 50000, "f", "Megalosaurus", );
//        deadMother.setDead(true);
//        Dinosaur aliveFather = new Dinosaur("aliveFather", 50000, "m", "Megalosaurus", );
//        aliveFather.setDead(true);
//        Dinosaur baby = new Dinosaur("baby", 20, "f", "Megalosaurus", deadMother, aliveFather, );
//
//        assertTrue(baby.areBothParentsDead());
//    }
//
//    @Test
//    void givenBaby_whenBothParentDied_thenParentsAreNotBothAlive() {
//        Dinosaur deadMother = new Dinosaur("deadMother", 50000, "f", "Megalosaurus", );
//        deadMother.setDead(true);
//        Dinosaur deadFather = new Dinosaur("deadFather", 50000, "m", "Megalosaurus", );
//        deadFather.setDead(true);
//        Dinosaur baby = new Dinosaur("baby", 20, "f", "Megalosaurus", deadMother, deadFather, );
//
//        assertTrue(baby.areBothParentsDead());
//    }
//
//    @Test
//    void whenCreateRootFather_thenCannotLooseParents() {
//        Dinosaur rootFather = new Dinosaur("rootFather", 50000, "m", "Megalosaurus", );
//
//        assertFalse(rootFather.areBothParentsDead());
//    }
//
//    @Test
//    void whenCreateRootMother_thenCannotLooseParents() {
//        Dinosaur rootMother = new Dinosaur("rootMother", 50000, "m", "Megalosaurus", );
//
//        assertFalse(rootMother.areBothParentsDead());
//    }
//
//    @Test
//    void givenBabyWithWeight20kg_whenAdding80kg_thenDinoWeightIs100kg() {
//        Dinosaur babyBecomesAdultDino = new Dinosaur("babyBecomesAdultDino", 20, "m",
//                "Megalosaurus", );
//
//        babyBecomesAdultDino.addWeight(80);
//
//        assertEquals(100, babyBecomesAdultDino.getWeight());
//    }
//
//    @Test
//    void whenBabyBecomesAdult_thenCannotLooseParents() {
//        Dinosaur father = new Dinosaur("mother", 50000, "f", "Megalosaurus", );
//        Dinosaur mother = new Dinosaur("father", 50000, "m", "Megalosaurus", );
//        Dinosaur babyBecomesAdultDino = new Dinosaur("babyBecomesAdultDino", 20, "m",
//                "Megalosaurus", mother, father, );
//
//        babyBecomesAdultDino.addWeight(80);
//
//        assertFalse(babyBecomesAdultDino.areBothParentsDead());
//    }
}