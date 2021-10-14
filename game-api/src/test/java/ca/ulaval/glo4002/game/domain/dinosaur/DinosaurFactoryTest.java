package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurFactoryTest {

    private DinosaurFactory dinosaurFactory;
    private DinosaurRepository dinosaurRepository;
    private static final int BABY_WEIGHT = 1;

    @BeforeEach
    void setUp() {
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        dinosaurRepository = new DinosaurRepositoryInMemory();
        dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);
    }

    //Test Dinosaur Adult
    @Test
    void givenADinosaur_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        Dinosaur dinosaurAdult = new Dinosaur("Effie", 89, "f", "Ankylosaurus");
        dinosaurRepository.save(dinosaurAdult);
        assertThrows(DuplicateNameException.class, () ->
                dinosaurFactory.create("Effie", 89, "f", "Ankylosaurus"));
    }

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
    @Test
    void givenADinosaurBaby_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        DinosaurBaby dinosaurBaby = new DinosaurBaby("Carey Price", BABY_WEIGHT, "m", "Ankylosaurus", "Bob", "Angela");
        dinosaurRepository.save(dinosaurBaby);
        assertThrows(DuplicateNameException.class, () ->
                dinosaurFactory.create("Carey Price", "Bob", "Angela", "m", "Ankylosaurus"));
    }

    @Test
    void givenADinosaurBaby_whenCreating_thenWeightIsOne(){
        DinosaurBaby dinosaurBaby = new DinosaurBaby("Carey Price", BABY_WEIGHT, "m", "Ankylosaurus", "Bob", "Angela");
        assertEquals(1, dinosaurBaby.getWeight());
    }
}