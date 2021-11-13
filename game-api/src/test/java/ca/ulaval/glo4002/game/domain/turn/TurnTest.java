package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class TurnTest {
    private List<Action> actions;
    private Turn turn;
    private TurnRepositoryInMemory turnRepositoryInMemory;
    private  TurnFactory turnFactory;

    @BeforeEach
    void createTurn() {
        turnFactory = new TurnFactory();
        actions = new ArrayList<>();
        turn = new Turn(new TurnNumber(1), actions);
        turnRepositoryInMemory = new TurnRepositoryInMemory();
    }

    @Test
    void whenAsked_returnsActionsList() {
        assertEquals(actions, turn.getActions());
    }

    @Test
    void whenTurnIsPlayed_incrementsTurn() {
        turnFactory.create(new TurnNumber(1), actions);
        turnRepositoryInMemory.findGame().addTurn(turn);
        turnFactory.create(new TurnNumber(2), actions);
        turnRepositoryInMemory.findGame().addTurn(turn);

        assertEquals(new TurnNumber(2), turnRepositoryInMemory.findGame().lastTurnNumber());
    }
}
