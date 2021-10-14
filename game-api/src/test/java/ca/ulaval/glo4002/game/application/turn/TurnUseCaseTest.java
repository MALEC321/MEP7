package ca.ulaval.glo4002.game.application.turn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.game.application.manager.ZooManager;
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
    private ZooManager zooManager;
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

    @Test
    void whenDinosaurIsStarving_ThenDinosaurShouldBeRemove() {
        List<Dinosaur> dinosaurs = new ArrayList<>(List.of(dinosaurTest));
        when(zooManager.feedThenCheckIfStarving(pantry, dinosaurTest)).thenReturn(true);
        turnUseCase.feedDinosaursByDietType(dinosaurs);

        verify(dinosaurRepository, times(1)).remove(dinosaurTest);
    }
}