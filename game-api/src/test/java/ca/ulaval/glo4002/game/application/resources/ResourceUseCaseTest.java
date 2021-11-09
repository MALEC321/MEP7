package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourceAssemblers;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ResourceUseCaseTest {

    private static final int QYT_BURGER = 4;
    private static final int QYT_SALAD = 5;
    private static final int QYT_WATER = 6;
    private ResourceUseCase resourceUseCase;
    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        ResourceFactory resourceFactory = new ResourceFactory();
        ResourceAssemblers resourceAssemblers = new ResourceAssemblers();
        ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ResourcesDistributor resourcesDistributor = new ResourcesDistributor();
        ActionFactory actionFactory = new ActionFactory();

        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        HerdRepository herdRepository = new HerdRepositoryInMemory();

        turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, herdRepository, actionRepository, resourcesDistributor);
        resourceUseCase = new ResourceUseCase(resourceFactory, resourceRepository, resourceAssemblers, actionRepository, actionFactory);
    }

    @Test
    public void givenResource_whenCreateOne_ActionsListNotEmpty() {
        ResourceCreationDto resourceCreationDto = new ResourceCreationDto(QYT_BURGER, QYT_SALAD, QYT_WATER);

        resourceUseCase.createResource(resourceCreationDto);
        List<ResourceDto> actionList = resourceUseCase.getAllResources();

        assertFalse(actionList.isEmpty());
    }

    @Test
    public void givenResource_ZeroCreated_ActionsListIsEmpty() {
        List<ResourceDto> actionList = resourceUseCase.getAllResources();

        assertFalse(actionList.isEmpty());
    }

    @Test
    public void givenResource_OneTurn_ResourceRepoNotEmpty() {
        ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
        turnUseCase.executeTurn();

        List<Resource> resources = resourceRepository.findAll();

        assertFalse(resources.isEmpty());
    }
}