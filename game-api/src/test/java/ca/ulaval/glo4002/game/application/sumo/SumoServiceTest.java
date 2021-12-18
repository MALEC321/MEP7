package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SumoServiceTest {
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
    private List<Action> actions;
    private Herd realHerd;

    @InjectMocks
    private SumoService sumoService;

    @Mock
    private Herd herd;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private ActionFactory actionFactory;
    @Mock
    private Action fightAction;
    @Mock
    private Game game;
    @Mock
    private Turn turn;
    @Mock
    private SumoDto sumoDto;
    @Mock
    private Dinosaur challenger;
    @Mock
    private Dinosaur challengee;
    @Mock
    private SumoResponse sumoResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void givenSumoDto_whenFight_thenAddFightActionToTurn() {
        when (herdRepository.findCurrent()).thenReturn(herd);
        when (gameRepository.findCurrent()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);

        sumoService.fight(sumoDto);

        verify(turn).addAction(fightAction);
    }

    @Test
    void givenSumoDto_whenFight_thenSaveGameToGameRepository() {
        when (herdRepository.findCurrent()).thenReturn(herd);
        when (gameRepository.findCurrent()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);
        sumoService.fight(sumoDto);

        verify(gameRepository).save(game);
    }

    @Test
    void givenSumoDto_whenFight_thenSumoResponseContainsThePredictedWinner() {
        String winner = "nameOfWinner";

        when (herdRepository.findCurrent()).thenReturn(herd);
        when (gameRepository.findCurrent()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
        when(actionFactory.createFight(turn.getActions(), challenger, challengee, herd)).thenReturn(fightAction);
        when(herd.fight(challenger, challengee, false)).thenReturn(winner);

        sumoService.fight(sumoDto).getPredictedWinner();

        //verify(sumoResponse).acceptParameter(result);

        assertEquals(sumoResponse, sumoService.fight(sumoDto).getPredictedWinner());
    }
}