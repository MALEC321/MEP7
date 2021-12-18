package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.PantryRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourcesServiceTest {
    private static final int QYT_BURGER = 4;
    private static final int QYT_SALAD = 5;
    private static final int QYT_WATER = 6;
    @Mock
    private ResourcesService resourcesService;
    @Mock
    private TurnService turnService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenResource_whenCreateOne_ActionsListNotEmpty() {
        ResourceCreationDto resourceCreationDto = new ResourceCreationDto(QYT_BURGER, QYT_SALAD, QYT_WATER);

        resourcesService.createResources(resourceCreationDto);
        List<ResourcesDto> actionList = resourcesService.getAllResources();

        assertTrue(actionList.isEmpty());
    }

    @Test
    public void givenResource_ZeroCreated_ActionsListIsEmpty() {
        List<ResourcesDto> actionList = resourcesService.getAllResources();

        assertTrue(actionList.isEmpty());
    }

    @Test
    public void givenResource_OneTurn_ResourceRepoNotEmpty() {
        PantryRepository resourceRepository = new PantryRepositoryInMemory();
        turnService.playTurn();

        List<ResourcesGroup> resources = resourceRepository.findCurrent().findAllResourcesGroup();

        assertFalse(resources.isEmpty());
    }
}