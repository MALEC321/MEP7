package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameRepositoryInMemoryTest {
    private TurnNumber firstTurnNumber = new TurnNumber(1);
    private TurnNumber secondTurnNumber = new TurnNumber(2);
    private GameRepositoryInMemory turnRepositoryInMemory;
    private List<Action> actions;
    private TurnFactory turnFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        turnFactory = new TurnFactory();
        this.actions = new ArrayList<>();
        this.turnRepositoryInMemory = new GameRepositoryInMemory();
    }

    @Test
    void givenATurn_whenFindingThatTurnById_thenThatTurnIsReturned() {
        Turn turn1 = turnFactory.create(secondTurnNumber, actions);
        turnRepositoryInMemory.findCurrentGame().addTurn(turn1);

        Turn turnFound = turnRepositoryInMemory.findCurrentGame().findByNumber(turn1.getTurnNumber());

        assertNotNull(turn1);
        assertEquals(turn1.getTurnNumber(), turnFound.getTurnNumber());
    }

    @Test
    void givenATurn_whenGetOneTurnWithTheBadId_thenNoneTurnIsReturned() {
        Turn turn1 = turnFactory.create(firstTurnNumber, actions);
        turnRepositoryInMemory.findCurrentGame().addTurn(turn1);

        assertNull(turnRepositoryInMemory.findCurrentGame().findByNumber(secondTurnNumber));
    }

    @Test
    void givenTurns_whenResetting_thenRepoContainsNoMoreTurn() {
        Turn turn1 = turnFactory.create(firstTurnNumber, actions);
        turnRepositoryInMemory.findCurrentGame().addTurn(turn1);
        Turn turn2 = turnFactory.create(secondTurnNumber, actions);
        turnRepositoryInMemory.findCurrentGame().addTurn(turn2);

        turnRepositoryInMemory.deleteAll();

        assertNotEquals(turn1, turnRepositoryInMemory.findCurrentGame().findByNumber(firstTurnNumber));
        assertNotEquals(turn2, turnRepositoryInMemory.findCurrentGame().findByNumber(secondTurnNumber));
    }
}