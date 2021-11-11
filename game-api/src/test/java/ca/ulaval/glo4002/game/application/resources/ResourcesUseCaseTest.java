package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.PantryRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ResourcesUseCaseTest {

    private static final int QYT_BURGER = 4;
    private static final int QYT_SALAD = 5;
    private static final int QYT_WATER = 6;
    private ResourcesUseCase resourcesUseCase;
    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        ResourcesGroupFactory resourcesGroupFactory = new ResourcesGroupFactory();
        ResourcesAssembler resourcesAssembler = new ResourcesAssembler();
        PantryRepository resourceRepository = new PantryRepositoryInMemory();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ResourcesDistributor resourcesDistributor = new ResourcesDistributor();
        ActionFactory actionFactory = new ActionFactory();
        ResourcesFactory resourcesFactory = new ResourcesFactory();

        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        HerdRepository herdRepository = new HerdRepositoryInMemory();

        turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, herdRepository, actionRepository, resourcesDistributor, resourcesFactory);
        resourcesUseCase = new ResourcesUseCase(resourcesGroupFactory, resourceRepository, resourcesAssembler, actionRepository, actionFactory);
    }

    @Test
    public void givenResource_whenCreateOne_ActionsListNotEmpty() {
        ResourceCreationDto resourceCreationDto = new ResourceCreationDto(QYT_BURGER, QYT_SALAD, QYT_WATER);

        resourcesUseCase.createResources(resourceCreationDto);
        List<ResourcesDto> actionList = resourcesUseCase.getAllResources();

        assertFalse(actionList.isEmpty());
    }

    @Test
    public void givenResource_ZeroCreated_ActionsListIsEmpty() {
        List<ResourcesDto> actionList = resourcesUseCase.getAllResources();

        assertFalse(actionList.isEmpty());
    }

    @Test
    public void givenResource_OneTurn_ResourceRepoNotEmpty() {
        PantryRepository resourceRepository = new PantryRepositoryInMemory();
        turnUseCase.executeTurn();

        List<ResourcesGroup> resources = resourceRepository.findPantry().findAll();

        assertFalse(resources.isEmpty());
    }
}