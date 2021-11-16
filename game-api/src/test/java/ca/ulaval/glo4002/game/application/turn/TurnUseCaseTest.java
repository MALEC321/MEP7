package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TurnUseCaseTest {

    public static final int EXPECTED = 1;

    private HerdRepository herdRepository;
    private ResourcesDistributor resourcesDistributor;
    private TurnFactory turnFactory;
    private ActionRepository actionRepository;
    private GameRepository gameRepository;
    private PantryRepository pantryRepository;
    private ResourcesFactory resourcesFactory;
    private Dinosaur dinosaurTest;
    private TurnUseCase turnUseCase;
    private Game game;


    @Before
    public void setUp() {

        dinosaurTest= new Dinosaur("DinoTest", 1, "m", "Ankylosaurus");
        game = new Game();
        gameRepository = mock(GameRepository.class);
        turnFactory = mock(TurnFactory.class);
        pantryRepository = mock(PantryRepository.class);
        herdRepository = mock(HerdRepository.class);
        actionRepository = mock(ActionRepository.class);
        resourcesDistributor = mock(ResourcesDistributor.class);
        resourcesFactory = mock(ResourcesFactory.class);
        turnUseCase = new TurnUseCase(turnFactory, gameRepository, pantryRepository, herdRepository, actionRepository,resourcesDistributor, resourcesFactory);

    }

    @Test
   public void whenGetTurnNumber_thenReturnedCurrentTurnNumber(){
        when(gameRepository.findGame()).thenReturn(game);

        turnUseCase.getTurnNumber();

        assertEquals(EXPECTED, game.currentTurnNumber().nextTurnNumber().getNumber());

    }

    @Test
    public void whenResetGame_thenReturnedNewGame(){
        turnUseCase.resetGame();

        verify(gameRepository).deleteAll();
    }

    @Test
    public void whenResetGame_thenReturnedDeletedPantry(){
        turnUseCase.resetGame();

        verify(pantryRepository).deleteAll();
    }

    @Test
    public void whenResetGame_thenReturnedDeletedHerd(){
        turnUseCase.resetGame();

        verify(herdRepository).deleteAll();
    }

    @Test
    public void whenResetGame_thenReturnedDeletedActions(){

        turnUseCase.resetGame();

        verify(actionRepository).deleteAll();

    }

}