package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class PantryRepositoryInMemoryTest {
    @InjectMocks
    private PantryRepositoryInMemory resourceRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void repoCreated_whenFindPantry_thenThatPantryIsFound() {
        assertEquals(pantry, this.resourceRepositoryInMemory.findPantry());
    }

    @Test
    void repoCreated_whenResetResource_thenPantryIsCleared() {
        this.resourceRepositoryInMemory.deleteAll();
        verify(this.pantry).clear();
    }
}
