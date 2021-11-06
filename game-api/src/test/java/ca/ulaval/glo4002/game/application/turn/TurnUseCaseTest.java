package ca.ulaval.glo4002.game.application.turn;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.game.domain.resources.FoodDistributor;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

class TurnUseCaseTest {

    @Mock
    private ResourceRepository resourceRepository;
    @Mock
    private DinosaurRepository dinosaurRepository;
    @Mock
    private FoodDistributor foodDistributor;
    @Mock
    private Pantry pantry;

    private final Dinosaur dinosaurTest = new Dinosaur("DinoTest", 1, "m", "Ankylosaurus");

    @InjectMocks
    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(resourceRepository.getPantry()).thenReturn(pantry);
    }

/*    @Test
    void whenDinosaurIsStarving_ThenDinosaurShouldBeRemove() {
        List<Dinosaur> dinosaurs = new ArrayList<>(List.of(dinosaurTest));
        //when(zooManager.feedDinosaur(pantry, dinosaurTest));
        turnUseCase.feedDinosaursByDietType(dinosaurs);

        verify(dinosaurRepository, times(1)).remove(dinosaurTest);
    }*/
}