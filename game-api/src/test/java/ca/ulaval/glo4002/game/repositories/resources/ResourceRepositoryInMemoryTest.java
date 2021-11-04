package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.domain.resources.Resource;
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
import ca.ulaval.glo4002.game.domain.resources.Water;

class ResourceRepositoryInMemoryTest {

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
        Resource resource = Mockito.mock(Resource.class);

        this.resourceRepositoryInMemory.add(resource);

        verify(this.pantry, times(1)).add(resource);
    }

    @Test
    void reposWithBurger_whenFindAllResource_thenRepoIsNotEmpty() {
        Resource resource = new Burger(1);

        this.resourceRepositoryInMemory.add(resource);

        assertFalse(this.resourceRepositoryInMemory.findAll().isEmpty());
    }

    @Test
    void repoWithResource_whenDecreaseExpirationDateResource_thenResourceAreDecreased() {
        Resource resource = new Burger(1);

        this.resourceRepositoryInMemory.add(resource);
        this.resourceRepositoryInMemory.decreaseExpirationDate();

        verify(this.pantry, times(1)).decreaseExpirationDate();
    }

    @Test
    void reposWithResource_whenResetResource_thenResourceAreReset() {
        Resource resource = new Water(10000);

        this.resourceRepositoryInMemory.add(resource);
        this.resourceRepositoryInMemory.reset();

        verify(this.pantry, times(1)).clear();
    }
}