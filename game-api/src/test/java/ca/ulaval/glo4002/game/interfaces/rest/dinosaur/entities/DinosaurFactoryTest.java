package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurFactoryTest {
    private DinosaurFactory dinosaurFactory;
    private SpeciesDietsCorrespondances speciesDietsCorrespondances;

    @BeforeEach
    void setUp() {
        DinosaurRepository dinosaurRepository = mock(DinosaurRepository.class);
        DuplicateNameException duplicateNameException = mock(DuplicateNameException.class);
        dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);

        Dinosaur dinosaur = new Dinosaur("name", 89, "f", "Ankylosaurus");
        when(dinosaurRepository.findByName("name")).thenReturn(dinosaur);
    }

//    @Test
//    void whenNameIsValid_thenCreateDinosaur() {
//        dinosaurFactory.create("anotherName", 89, "f", "Ankylosaurus");
//
//        verify(duplicateNameException, never()).getCode();
//    }

//    @Test
//    void whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
//        assertThrows(DuplicateNameException.class, () ->
//                dinosaurFactory.create("name", 89, "f", "Ankylosaurus"));
//    }
}