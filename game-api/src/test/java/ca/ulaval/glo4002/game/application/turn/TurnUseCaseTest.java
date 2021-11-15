package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.GameRepositoryInMemory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TurnUseCaseTest {

    @Mock
    private PantryRepository resourceRepository;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private ResourcesDistributor resourcesDistributor;
    @Mock
    private Pantry pantry;
    @Mock
    private TurnFactory turnFactory;

    private GameRepository gameRepository;
    @Mock
    private PantryRepository pantryRepository;
    @Mock
    private ActionRepository actionRepository;
    @Mock
    private ResourcesFactory resourcesFactory;

    private Dinosaur dinosaurTest;

    @InjectMocks
    private TurnUseCase turnUseCase;

    private GameRepositoryInMemory gameRepositoryInMemory;

    Game game;




    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(resourceRepository.findPantry()).thenReturn(pantry);

        dinosaurTest= new Dinosaur("DinoTest", 1, "m", "Ankylosaurus");

         game = gameRepositoryInMemory.findGame();
    }

        @Test
    public void whenDinosaurIsStarving_ThenDinosaurShouldBeRemove() {
        List<Dinosaur> dinosaurs = new ArrayList<>(List.of(dinosaurTest));
        //when(zooManager.feedDinosaur(pantry, dinosaurTest));
       // turnUseCase.feedDinosaursByDietType(dinosaurs);

       // verify(dinosaurRepository, times(1)).remove(dinosaurTest);
    }

    @Test
   public void whenGetTurnNumber_thenReturnedCurrentTurnNumber(){
//        when(gameRepository.findGame()).thenReturn(game);
//
//        assertEquals(1, game.currentTurnNumber());

    }
}