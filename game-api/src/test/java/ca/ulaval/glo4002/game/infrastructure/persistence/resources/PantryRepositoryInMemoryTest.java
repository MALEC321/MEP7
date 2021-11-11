package ca.ulaval.glo4002.game.infrastructure.persistence.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class PantryRepositoryInMemoryTest {
    ResourcesFactory resourcesFactory = new ResourcesFactory();
    @InjectMocks
    private PantryRepositoryInMemory resourceRepositoryInMemory;

    @Mock
    private Pantry pantry;

    @Mock
    private ResourcesGroup resourcesGroup;

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
    void givenReposWithBurger_whenFindAllResource_thenRepoIsNotEmpty() {
        Resources resources = resourcesFactory.create(BURGER, 1);
        resourcesGroup.addResource(resources.getType(), resources.getQuantity());
        when(pantry.findAll()).thenReturn(Arrays.asList(resourcesGroup));

        this.resourceRepositoryInMemory.findPantry().addResources(resources);

        assertFalse(this.resourceRepositoryInMemory.findPantry().findAll().isEmpty());
    }

    @Test
    void givenRepoWithResource_whenDecreaseExpirationDateResource_thenResourceAreDecreased() {
        Resources resources = resourcesFactory.create(BURGER, 1);

        this.resourceRepositoryInMemory.findPantry().addResources(resources);
        this.resourceRepositoryInMemory.findPantry().decreaseExpirationDate();

        verify(this.pantry, times(1)).decreaseExpirationDate();
    }

    @Test
    void givenReposWithResource_whenResetResource_thenNoLeftResourcesInRepo() {
        Resources resources = resourcesFactory.create(WATER, 10000);
        resourcesGroup.addResource(resources.getType(), resources.getQuantity());
        when(pantry.findAll()).thenReturn(Collections.EMPTY_LIST);

        this.resourceRepositoryInMemory.findPantry().addResources(resources);
        this.resourceRepositoryInMemory.reset();

        verify(this.pantry, times(1)).clear();
        assertTrue(this.resourceRepositoryInMemory.findPantry().findAll().isEmpty());
    }

}
