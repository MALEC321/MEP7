package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class TurnTest {
    private UUID id;
    private List<Action> actions;
    private Turn turn;

    @BeforeEach
    void createTurn() {
        id = UUID.randomUUID();
        actions = new ArrayList<>();
        turn = new Turn(id, actions);
    }

    @Test
    void whenAsked_returnsCorrectId() {
        assertEquals(id, turn.getId());
        Turn.number = 1;
    }

    @Test
    void whenAsked_returnsActionsList() {
        assertEquals(actions, turn.getActions());
        Turn.number = 1;
    }

    @Test
    void whenTurnIsPlayed_incrementsTurn() {
        assertEquals(2, Turn.number);
    }
}
