package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class PantryRepositoryInMemoryTest {
    @InjectMocks
    private PantryRepositoryInMemory pantryRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenNoPantry_whenFindCurrentPantry_thenReturnsPantry() {
        assertNotNull(pantryRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenPantry_whenSave_thenFindCurrentPantryReturnsSavedPantry() {
        pantryRepositoryInMemory.save(pantry);
        assertEquals(pantry, pantryRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenPantry_whenResetCurrent_thenCurrentPantryIsReset() {
        pantryRepositoryInMemory.save(pantry);
        pantryRepositoryInMemory.deleteAll();

        assertNotEquals(pantry, pantryRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenNoPantry_whenSave_thenFindPreviousPantryThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> pantryRepositoryInMemory.findPrevious());
    }

    @Test
    public void givenOnePantrys_whenSave_thenFindPreviousPantryReturnsPantry() {
        pantryRepositoryInMemory.save(pantry);
        assertNotNull(pantryRepositoryInMemory.findPrevious());
    }

}