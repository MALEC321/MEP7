package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

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

class PantryRepositoryInMemoryTest {
    ResourcesFactory resourcesFactory = new ResourcesFactory();
    @InjectMocks
    private PantryRepositoryInMemory resourceRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void whenAddResource_thenResourceIsAddedToPantry() {
        Resources resources = Mockito.mock(Resources.class);

        this.resourceRepositoryInMemory.findPantry().addResources(resources);

        verify(this.pantry, times(1)).addResources(resources);
    }

    @Test
    void reposWithBurger_whenFindAllResource_thenRepoIsNotEmpty() {
        Resources resources = resourcesFactory.create(BURGER, 1);

        this.resourceRepositoryInMemory.findPantry().addResources(resources);

        assertFalse(this.resourceRepositoryInMemory.findPantry().findAll().isEmpty());
    }

    @Test
    void repoWithResource_whenDecreaseExpirationDateResource_thenResourceAreDecreased() {
        Resources resources = resourcesFactory.create(BURGER, 1);

        this.resourceRepositoryInMemory.findPantry().addResources(resources);
        this.resourceRepositoryInMemory.findPantry().decreaseExpirationDate();

        verify(this.pantry, times(1)).decreaseExpirationDate();
    }

//    @Test
//    void reposWithResource_whenResetResource_thenResourceAreReset() {
//        Resources resources = resourcesFactory.create(WATER, 10000);
//
//        this.resourceRepositoryInMemory.findPantry().addResources(resources);
//        this.resourceRepositoryInMemory.reset();
//
//        verify(this.pantry, times(1)).clear();
//    }
}