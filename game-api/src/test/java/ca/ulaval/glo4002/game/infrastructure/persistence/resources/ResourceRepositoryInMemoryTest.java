package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.domain.resources.Burger;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.Water;

public class ResourceRepositoryInMemoryTest {
    @InjectMocks
    private ResourceRepositoryInMemory resourceRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenAddResource_thenResourceIsAddedToPantry() {
        ResourceElements resourceElements = Mockito.mock(ResourceElements.class);

        this.resourceRepositoryInMemory.add(resourceElements);

        verify(this.pantry, times(1)).add(resourceElements);
    }

    @Test
    void reposWithBurger_whenFindAllResource_thenRepoIsNotEmpty() {
        ResourceElements resourceElements = new Burger(1);

        this.resourceRepositoryInMemory.add(resourceElements);

        assertFalse(this.resourceRepositoryInMemory.findAll().isEmpty());
    }

    @Test
    void repoWithResource_whenDecreaseExpirationDateResource_thenResourceAreDecreased() {
        ResourceElements resourceElements = new Burger(1);

        this.resourceRepositoryInMemory.add(resourceElements);
        this.resourceRepositoryInMemory.decreaseExpirationDate();

        verify(this.pantry, times(1)).decreaseExpirationDate();
    }

    @Test
    void reposWithResource_whenResetResource_thenResourceAreReset() {
        ResourceElements resourceElements = new Water(10000);

        this.resourceRepositoryInMemory.add(resourceElements);
        this.resourceRepositoryInMemory.reset();

        verify(this.pantry, times(1)).clear();
    }
}