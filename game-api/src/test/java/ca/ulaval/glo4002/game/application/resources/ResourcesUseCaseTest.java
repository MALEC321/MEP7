package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDto;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ResourcesUseCaseTest {

    private static final int QYT_BURGER = 4;
    private static final int QYT_SALAD = 5;
    private static final int QYT_WATER = 6;
    private ResourceUseCase resourceUseCase;
    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        ResourceGroupFactory resourceGroupFactory = new ResourceGroupFactory();
        ResourceAssembler resourceAssembler = new ResourceAssembler();
        ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ZooManager zooManager = new ZooManager();
        ActionFactory actionFactory = new ActionFactory();
        ResourcesFactory resourcesFactory = new ResourcesFactory();

        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        TurnAssembler turnAssembler = new TurnAssembler();
        DinosaurRepository dinosaurRepository = new DinosaurRepositoryInMemory();

        turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, dinosaurRepository, actionRepository, zooManager, resourcesFactory);
        resourceUseCase = new ResourceUseCase(resourceGroupFactory, resourceRepository, resourceAssembler, actionRepository, actionFactory);
    }

    @Test
    public void givenResource_whenCreateOne_ActionsListNotEmpty() {
        ResourceCreationDto resourceCreationDto = new ResourceCreationDto();
        resourceCreationDto.qtyBurger = QYT_BURGER;
        resourceCreationDto.qtySalad = QYT_SALAD;
        resourceCreationDto.qtyWater = QYT_WATER;

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
        turnUseCase.createTurn();

        List<ResourcesGroup> resources = resourceRepository.findAll();

        assertFalse(resources.isEmpty());
    }
}