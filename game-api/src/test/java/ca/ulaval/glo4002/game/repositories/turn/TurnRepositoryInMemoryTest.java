package ca.ulaval.glo4002.game.repositories.turn;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;

class TurnRepositoryInMemoryTest {
    private TurnRepositoryInMemory turnRepositoryInMemory;
    final private UUID fakeTurnId = UUID.fromString("4321-34-56-78-987654");

    @Mock
    private Turn turn1;

    @Mock
    private Turn turn2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.turnRepositoryInMemory = new TurnRepositoryInMemory();
    }

    @Test
    void givenATurn_whenFindingThatTurnById_thenThatTurnIsReturned() {
        UUID idTurn1 = setTurnMock1();

        turnRepositoryInMemory.save(turn1);
        Turn turn = turnRepositoryInMemory.findById(idTurn1);

        assertNotNull(turn);
        assertEquals(turn1.getId(), turnRepositoryInMemory.findById(idTurn1).getId());
    }

    @Test
    void whenGetOneTurnWithTheBadId_thenNoneTurnIsReturned() {
        turnRepositoryInMemory.save(turn1);

        assertNull(turnRepositoryInMemory.findById(fakeTurnId));
    }

    @Test
    void givenTurns_whenResetTurns_thenRepoContainsNoMoreTurn() {
        UUID idTurn1 = setTurnMock1();
        UUID idTurn2 = setTurnMock2();

        turnRepositoryInMemory.save(turn1);
        turnRepositoryInMemory.save(turn2);
        turnRepositoryInMemory.reset();

        assertNotEquals(turn1, turnRepositoryInMemory.findById(idTurn1));
        assertNotEquals(turn2, turnRepositoryInMemory.findById(idTurn2));
    }

    private UUID setTurnMock1() {
        UUID id = UUID.fromString("1234-12-34-56-123456");
        Mockito.when(turn1.getId()).thenReturn(id);
        return id;
    }

    private UUID setTurnMock2() {
        UUID id = UUID.fromString("5678-34-56-78-456789");
        Mockito.when(turn1.getId()).thenReturn(id);
        return id;
    }
}