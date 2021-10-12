package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.actions.AddDino;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnTest;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;

class TurnUseCaseTest {

    final int BURGER_QUANTITY = 100;
    final int SALAD_QUANTITY = 250;
    final int WATER_QUANTITY = 10000;

    private final TurnFactory turnFactory = new TurnFactory();
    private final TurnRepository turnRepository = new TurnRepositoryInMemory();
    private final ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
    private final ActionRepository actionRepository = new ActionRepositoryInMemory();
    private final DinosaurRepository dinosaurRepository = new DinosaurRepositoryInMemory();
    @Mock
    private ZooManager zooManager;
    private final Dinosaur dinosaurTest = new Dinosaur("DinoTest", 1, "m", "Ankylosaurus");



    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, dinosaurRepository, actionRepository);
    }

    void addOneHerbivoreDinosaur() {
        actionRepository.save(new AddDino(UUID.randomUUID(), dinosaurTest, dinosaurRepository));
        turnUseCase.createTurn();
    }

    @Test
    void createTurn() {
    }

    @Test
    void cookItShouldReturnExpectedQuantity() {
        turnUseCase.cookIt();

    }

    @Test
    void whenFeedHerbivoreDinosaurs_ThenFreshSaladAndWaterShouldBeRetrieve() {
        addOneHerbivoreDinosaur();

        int actualSaladQuantity = SALAD_QUANTITY - dinosaurTest.getFoodQuantityNeeded();
        int actualWaterQuantity = WATER_QUANTITY - dinosaurTest.getWaterQuantityNeeded();

        assertEquals(actualSaladQuantity, resourceRepository.findAll().get(0).getSaladQuantity());
        assertEquals(actualWaterQuantity, resourceRepository.findAll().get(0).getWaterQuantity());

    }

    @Test
    void whenFeedHerbivoreDinosaurs_ThenFreshWaterShouldBeRetrieve() {
    }

    @Test
    void removeBabyDinosaurs() {
    }

    @Test
    void reset() {
    }
}