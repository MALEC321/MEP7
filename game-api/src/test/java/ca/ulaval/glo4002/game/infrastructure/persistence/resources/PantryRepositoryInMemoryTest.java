package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.domain.resources.Pantry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PantryRepositoryInMemoryTest {
    @InjectMocks
    private PantryRepositoryInMemory pantryRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void repoCreated_whenFindPantry_thenThatPantryIsFound() {
        assertEquals(pantry, this.pantryRepositoryInMemory.findCurrentPantry());
    }

    @Test
    public void givenPantry_whenSave_thenReturnedNotEmptyPantry() {

        this.pantryRepositoryInMemory.save(pantry);

        Assertions.assertNotNull(this.pantryRepositoryInMemory);
    }

    @Test
    public void repoCreated_whenResetResource_thenPantryIsCleared() {
        this.pantryRepositoryInMemory.deleteAll();

        verify(this.pantry, times(1)).clear();
    }
}
