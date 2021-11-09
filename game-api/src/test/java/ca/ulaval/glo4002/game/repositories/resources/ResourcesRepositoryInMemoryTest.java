package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ResourcesRepositoryInMemoryTest {
    ResourcesFactory resourcesFactory = new ResourcesFactory();

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
        Resources resources = Mockito.mock(Resources.class);

        this.resourceRepositoryInMemory.add(resources);

        verify(this.pantry, times(1)).add(resources);
    }

    @Test
    void reposWithBurger_whenFindAllResource_thenRepoIsNotEmpty() {
        Resources resources = resourcesFactory.create(BURGER, 1);

        this.resourceRepositoryInMemory.add(resources);

        assertFalse(this.resourceRepositoryInMemory.findAll().isEmpty());
    }

    @Test
    void repoWithResource_whenDecreaseExpirationDateResource_thenResourceAreDecreased() {
        Resources resources = resourcesFactory.create(BURGER, 1);

        this.resourceRepositoryInMemory.add(resources);
        this.resourceRepositoryInMemory.decreaseExpirationDate();

        verify(this.pantry, times(1)).decreaseExpirationDate();
    }

    @Test
    void reposWithResource_whenResetResource_thenResourceAreReset() {
        Resources resources = resourcesFactory.create(WATER, 10000);

        this.resourceRepositoryInMemory.add(resources);
        this.resourceRepositoryInMemory.reset();

        verify(this.pantry, times(1)).clear();
    }
}