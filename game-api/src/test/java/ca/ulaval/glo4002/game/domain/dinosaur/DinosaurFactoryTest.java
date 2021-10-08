package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DinosaurFactoryTest {
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
}