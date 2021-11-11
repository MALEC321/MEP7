package ca.ulaval.glo4002.game.domain.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.domain.turn.aggregate.Turn;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ulaval.glo4002.game.domain.actions.Action;

public class TurnTest {
    private String random;
    private List<Action> actions;
    private Turn turn;
    private TurnRepositoryInMemory turnRepositoryInMemory;
    private  TurnFactory turnFactory;

    @BeforeEach
    void createTurn() {
        turnFactory = new TurnFactory();
        random = UUID.randomUUID().toString().toUpperCase();
        actions = new ArrayList<>();
        turn = new Turn(random.substring(0, random.indexOf("-")), actions);
        turnRepositoryInMemory = new TurnRepositoryInMemory();
    }

    @Test
    void whenAsked_returnsCorrectId() {
        assertEquals(random.substring(0, random.indexOf("-")), turn.getTurnNumber());
    }

    @Test
    void whenAsked_returnsActionsList() {
        assertEquals(actions, turn.getActions());
    }

    @Test
    void whenTurnIsPlayed_incrementsTurn() {
        turnFactory.create(random, actions);
        turnRepositoryInMemory.findTurns().addTurn(turn);
        turnFactory.create(random, actions);
        turnRepositoryInMemory.findTurns().addTurn(turn);

        assertEquals(2, turnRepositoryInMemory.findTurns().numberOfTurns());
    }
}
