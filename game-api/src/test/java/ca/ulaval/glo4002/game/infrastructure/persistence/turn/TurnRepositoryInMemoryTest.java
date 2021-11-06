package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.aggregate.TurnId;
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
        String idTurn1 = turnRepositoryInMemory.nextTurnId();
        TurnId turn1 = turnFactory.create(idTurn1, actions);

        turnRepositoryInMemory.save(turn1);
        TurnId turnFound = turnRepositoryInMemory.findById(turn1.getTurnId());

        assertNotNull(turn1);
        assertEquals(turn1.getTurnId(), turnFound.getTurnId());
    }

    @Test
    void whenGetOneTurnWithTheBadId_thenNoneTurnIsReturned() {
        TurnId turn1 = turnFactory.create("123456789", actions);
        turnRepositoryInMemory.save(turn1);

        assertNull(turnRepositoryInMemory.findById(fakeTurnId));
    }

    @Test
    void givenTurns_whenResetTurns_thenRepoContainsNoMoreTurn() {
        String idTurn1 = turnRepositoryInMemory.nextTurnId();
        String idTurn2 = turnRepositoryInMemory.nextTurnId();
        TurnId turn1 = turnFactory.create(idTurn1, actions);
        TurnId turn2 = turnFactory.create(idTurn2, actions);

        turnRepositoryInMemory.save(turn1);
        turnRepositoryInMemory.save(turn2);
        turnRepositoryInMemory.reset();

        assertNotEquals(turn1, turnRepositoryInMemory.findById(idTurn1));
        assertNotEquals(turn2, turnRepositoryInMemory.findById(idTurn2));
    }
}