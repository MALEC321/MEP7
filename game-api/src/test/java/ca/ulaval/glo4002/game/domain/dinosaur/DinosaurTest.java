package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DinosaurTest {

    private DinosaurFactory dinosaurFactory;
    @BeforeEach
    void setUp() {

        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurRepository dinosaurRepository = mock(DinosaurRepository.class);
        dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);

        Dinosaur dinosaur = new DinosaurAdult("Effie", 89, "f", "Ankylosaurus");
        when(dinosaurRepository.findByName("Effie")).thenReturn(dinosaur);
    }

    @Test
    void whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        assertThrows(DuplicateNameException.class, () ->
                dinosaurFactory.create("Effie", 89, "f", "Ankylosaurus"));
    }

    @Test
    void whenGenderIsInvalid_thenThrowsInvalidGenderException() {
        assertThrows(InvalidGenderException.class, () ->
                dinosaurFactory.create("Charly", 89, "x", "Ankylosaurus"));
    }

    @Test
    void whenWeightIsNegative_thenThrowsInvalidWeightException() {
        assertThrows(InvalidWeightException.class, () ->
                dinosaurFactory.create("George", -3, "f", "Ankylosaurus"));
    }

    @Test
    void whenSpecieIsNotSupported_thenThrowsInvalidSpeciesException() {
        assertThrows(InvalidSpeciesException.class, () ->
                dinosaurFactory.create("Thom", 89, "f", "Raptors"));
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateStrength() {
        Dinosaur dinosaur = dinosaurFactory.create("Alaska", 1000, "f", "Allosaurus");
        assertEquals(2250, dinosaur.getStrength());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateWaterNeeded() {
        Dinosaur dinosaur = dinosaurFactory.create("Will", 1000, "f", "Allosaurus");
        assertEquals(1200, dinosaur.feedWater());
    }

    @Test
    void whenCreatingDinosaur_shouldCalculateFoodNeeded() {
        Dinosaur dinosaur = dinosaurFactory.create("Cherry", 1000, "f", "Allosaurus");
        assertEquals(2, dinosaur.feedFood());
    }
}