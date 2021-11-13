package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidWeightException;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DinosaurFactoryTest {
    private DinosaurFactory dinosaurFactory;
    private HerdRepository herdRepository;
    private static final int BABY_WEIGHT = 1;

    @BeforeEach
    void setUp() {
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        herdRepository = new HerdRepositoryInMemory();
        dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietsCorrespondances);
    }

    //Test Dinosaur Adult
 /*   @Test
    void givenADinosaur_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        Dinosaur dinosaurAdult = new Dinosaur("Effie", 89, "f", "Ankylosaurus");
        dinosaurRepository.save(dinosaurAdult);
        assertThrows(DuplicateNameException.class, () ->
            dinosaurFactory.create("Effie", 89, "f", "Ankylosaurus"));
    }*/

    @Test
    void givenADinosaur_whenGenderIsInvalid_thenThrowsInvalidGenderException() {
        assertThrows(InvalidGenderException.class, () ->
            dinosaurFactory.create("Charly", 89, "x", "Ankylosaurus"));
    }

    @Test
    void givenADinosaur_whenWeightIsNegative_thenThrowsInvalidWeightException() {
        assertThrows(InvalidWeightException.class, () ->
            dinosaurFactory.create("George", -3, "f", "Ankylosaurus"));
    }

    @Test
    void givenADinosaur_whenSpecieIsNotSupported_thenThrowsInvalidSpeciesException() {
        assertThrows(InvalidSpeciesException.class, () ->
            dinosaurFactory.create("Thom", 89, "f", "Raptors"));
    }

    //Test Dinosaur Baby
/*    @Test
    void givenADinosaurBaby_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        DinosaurBaby dinosaurBaby = new DinosaurBaby("Carey Price", BABY_WEIGHT, "m", "Ankylosaurus", "Bob", "Angela");
        dinosaurRepository.save(dinosaurBaby);
        assertThrows(DuplicateNameException.class, () ->
            dinosaurFactory.create("Carey Price", "Bob", "Angela", "m", "Ankylosaurus"));
    }*/

/*    @Test
    void givenADinosaurBaby_whenCreating_thenWeightIsOne() {
        DinosaurBaby dinosaurBaby = new DinosaurBaby("Carey Price", BABY_WEIGHT, "m", "Ankylosaurus", "Bob", "Angela");
        assertEquals(1, dinosaurBaby.getWeight());
    }*/

    @Test
    void givenBabyDinosInfo_whenCreating_thenBabyIsCreated() {
        Dinosaur mother = new Dinosaur("mother", 50000, "f", "Megalosaurus");
        Dinosaur father = new Dinosaur("father", 50000, "m", "Megalosaurus");
        Dinosaur dinosaurBaby = new Dinosaur("Carey Price", BABY_WEIGHT, "m", "Ankylosaurus",
                mother, father);

        Dinosaur currentDino = dinosaurFactory.create("Carey Price", father, mother, "m", "Ankylosaurus");

        assertEquals(dinosaurBaby, currentDino);
    }

    @Test
    void givenBabyDinosInfo_whenCreating_thenBabyHaveParents() {
        Dinosaur mother = new Dinosaur("mother", 50000, "f", "Megalosaurus");
        Dinosaur father = new Dinosaur("father", 50000, "m", "Megalosaurus");

        Dinosaur currentDino = dinosaurFactory.create("Carey Price", father, mother, "m", "Ankylosaurus");

       assertFalse(currentDino.areBothParentsDead());
    }
}