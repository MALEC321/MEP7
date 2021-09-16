package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DinosaurFactoryTest {
    private DinosaurFactory dinosaurFactory;
    private DuplicateNameException duplicateNameException;

    @BeforeEach
    void setUp() {
        DinosaurRepository dinosaurRepository = mock(DinosaurRepository.class);
        duplicateNameException = mock(DuplicateNameException.class);
        dinosaurFactory = new DinosaurFactory(dinosaurRepository);
        Dinosaur dinosaur = new Dinosaur("name", 89, "f", "");
        when(dinosaurRepository.findByName("name")).thenReturn(dinosaur);
    }

    @Test
    void whenNameIsValid_thenCreateDinosaur() {
        dinosaurFactory.create("anotherName", 89, "f", "");

        verify(duplicateNameException, never()).getCode();
    }


    @Test
    void whenNameIsInvalid_thenCreateDinosaur() {
        assertThrows(DuplicateNameException.class, () -> {
            dinosaurFactory.create("name", 89, "f", "");
        });
    }
}