package ca.ulaval.glo4002.game.application.turn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.ulaval.glo4002.game.domain.actions.AddDino;
import ca.ulaval.glo4002.game.domain.dinosaur.DietStrategyForHerbivore;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;


public class TurnServiceTest {

    private final Dinosaur dinosaurTest = new Dinosaur("DinoTest", 1, "m", new DietStrategyForHerbivore(DietType.HERBIVORE), "Ankylosaurus");
    private final int currentNumber = 435;
    private final int nextNumber = 435;
    private final Turn nextTurn = new Turn(new TurnNumber(nextNumber));
    private final TurnNumber turnNumber = new TurnNumber(currentNumber);
    @Spy
    private Turn turn = new Turn(turnNumber);
    @Mock
    private PantryRepository resourceRepository;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private Pantry pantry;
    @Mock
    private TurnEffect turnEffect;
    @Mock
    private ActionService actionService;
    @InjectMocks
    private TurnService turnService;
    @Mock
    private TurnFactory turnFactory;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private PantryRepository pantryRepository;
    @Mock
    private Game game;
    @Mock
    private Herd herd;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(resourceRepository.findCurrent()).thenReturn(pantry);
        when(gameRepository.findCurrent()).thenReturn(game);
        when(herdRepository.findCurrent()).thenReturn(herd);
        when(game.currentTurn()).thenReturn(turn);
        when(pantryRepository.findCurrent()).thenReturn(pantry);
        when(turnFactory.create(game.nextTurnNumber())).thenReturn(nextTurn);
    }

    @Test
    void whenPlayTurn_ThenAllTurnStepAreDone() {
        turnService.playTurn();

        verify(turnEffect).addTurnConsequences(turn, herd, pantry);
        verify(turnEffect).endTurn(game);
    }

    @Test
    void givenActions_afterPlayTurn_thenActionListAreEmpty() {
        turn.addAction(new AddDino(dinosaurTest, herd));

        turnService.playTurn();
        assertEquals(0, turn.getActions().size());
    }

    @Test
    public void givenATurn_whenResetTurn_thenTurnIsReset() {
        turnService.resetGame();

        verify(gameRepository).deleteAll();
        verify(pantryRepository).deleteAll();
        verify(herdRepository).deleteAll();
    }

    @Test
    void givenEmptyActions_whenPlayTurn_thenTurnNumberIsIncreaseByOne() {
        turnService.playTurn();

        assertEquals(nextNumber, nextTurn.getTurnNumber().getNumber());
    }

    @Test
    public void givenATurn_whenGetTurnNumber_thenReturnsCurrentTurnNumber() {
        when(gameRepository.findCurrent()).thenReturn(game);

        turnService.getLastPlayedTurnNumber();

        verify(game).lastPlayedTurnNumber();
    }


}
