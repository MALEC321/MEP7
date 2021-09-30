package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurFactoryTest {
    private DinosaurFactory dinosaurFactory;
    private DuplicateNameException duplicateNameException;
    private SpeciesDietsCorrespondances speciesDietsCorrespondances;

    @BeforeEach
    void setUp() {
        DinosaurRepository dinosaurRepository = mock(DinosaurRepository.class);
        duplicateNameException = mock(DuplicateNameException.class);
        dinosaurFactory = new DinosaurFactory(dinosaurRepository,speciesDietsCorrespondances);
        Dinosaur dinosaur = new Dinosaur("name", 89, "f", "Ankylosaurus", "Herbivore");
        when(dinosaurRepository.findByName("name")).thenReturn(dinosaur);
    }

//    @Test
//    void whenNameIsValid_thenCreateDinosaur() {
//        dinosaurFactory.create("anotherName", 89, "f", "Ankylosaurus");
//
//        verify(duplicateNameException, never()).getMessage();
//    }

    @Test
    void whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        assertThrows(DuplicateNameException.class, () -> {
            dinosaurFactory.create("name", 89, "f", "Ankylosaurus");
        });
    }

    @Test
    void when1000kgFemaleAnkylosaurusIsCreated_thenForceIsEqualTo() {
        Dinosaur dinosaur = new Dinosaur("name", 1000, "f", "Triceratops", "Carnivore");

        assertEquals(2250, dinosaur.getForce());
    }
}