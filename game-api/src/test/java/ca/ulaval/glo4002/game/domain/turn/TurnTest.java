package ca.ulaval.glo4002.game.domain.turn;

import ca.ulaval.glo4002.game.domain.actions.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class TurnTest {
    private static final int FIRST_TURN_NUMBER = 1;
    private List<Action> actions;

    @InjectMocks
    private Turn turn;

    @Mock
    private Action action;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        actions = new ArrayList<>();
        actions.add(action);
        turn = new Turn(new TurnNumber(FIRST_TURN_NUMBER), actions);
    }

    @Test
    public void whenAsked_returnsActionsList() {
        assertEquals(actions, turn.getActions());
    }

    @Test
    public void turnWithNumber_whenFirstTurnIsPlayed_thenTurnNumberIsone() {
        assertEquals(FIRST_TURN_NUMBER, turn.getTurnNumber().getNumber());
    }

    @Test
    public void firstTurn_whenTurnIsPlayed_thenActionIsExecuted() {
        turn.executeActions();

        verify(action).execute();
    }
}
