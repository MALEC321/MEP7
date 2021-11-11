package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.aggregate.Turn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class TurnRepositoryInMemoryTest {
    private TurnRepositoryInMemory turnRepositoryInMemory;
    private List<Action> actions;
    private TurnFactory turnFactory;
    final private String fakeTurnId = UUID.randomUUID().toString().toUpperCase();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        turnFactory = new TurnFactory();
        this.actions = new ArrayList<>();
        this.turnRepositoryInMemory = new TurnRepositoryInMemory();
    }

    @Test
    void givenATurn_whenFindingThatTurnById_thenThatTurnIsReturned() {
        String idTurn1 = turnRepositoryInMemory.findTurns().nextTurnNumber();
        Turn turn1 = turnFactory.create(idTurn1, actions);

        turnRepositoryInMemory.findTurns().addTurn(turn1);
        Turn turnFound = turnRepositoryInMemory.findTurns().findByNumber(turn1.getTurnNumber());

        assertNotNull(turn1);
        assertEquals(turn1.getTurnNumber(), turnFound.getTurnNumber());
    }

    @Test
    void whenGetOneTurnWithTheBadId_thenNoneTurnIsReturned() {
        Turn turn1 = turnFactory.create("123456789", actions);
        turnRepositoryInMemory.findTurns().addTurn(turn1);


        assertNull(turnRepositoryInMemory.findTurns().findByNumber(fakeTurnId));
    }

    @Test
    void givenTurns_whenResetTurns_thenRepoContainsNoMoreTurn() {
        String idTurn1 = turnRepositoryInMemory.findTurns().nextTurnNumber();
        Turn turn1 = turnFactory.create(idTurn1, actions);
        turnRepositoryInMemory.findTurns().addTurn(turn1);


        String idTurn2 = turnRepositoryInMemory.findTurns().nextTurnNumber();
        Turn turn2 = turnFactory.create(idTurn2, actions);
        turnRepositoryInMemory.findTurns().addTurn(turn2);

        turnRepositoryInMemory.reset();

        assertNotEquals(turn1, turnRepositoryInMemory.findTurns().findByNumber(idTurn1));
        assertNotEquals(turn2, turnRepositoryInMemory.findTurns().findByNumber(idTurn2));
    }
}