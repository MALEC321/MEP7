package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class TurnServiceTest {

    @Mock
    private PantryRepository resourceRepository;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private ResourcesDistributor resourcesDistributor;
    @Mock
    private Pantry pantry;

    private final Dinosaur dinosaurTest = new Dinosaur("DinoTest", 1, "m", "Ankylosaurus");

    @InjectMocks
    private TurnService turnService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(resourceRepository.findPantry()).thenReturn(pantry);
    }

    /*    @Test
    void whenDinosaurIsStarving_ThenDinosaurShouldBeRemove() {
        List<Dinosaur> dinosaurs = new ArrayList<>(List.of(dinosaurTest));
        //when(zooManager.feedDinosaur(pantry, dinosaurTest));
        turnUseCase.feedDinosaursByDietType(dinosaurs);

        verify(dinosaurRepository, times(1)).remove(dinosaurTest);
    }*/
}