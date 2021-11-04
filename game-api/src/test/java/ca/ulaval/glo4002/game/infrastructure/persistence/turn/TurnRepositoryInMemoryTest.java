package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

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

    private TurnRepository turnRepository;
    private UUID fakeTurnId = UUID.fromString("4321-34-56-78-987654");

    @Mock
    private Turn turn1;

    @Mock
    private Turn turn2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.turnRepository = new TurnRepositoryInMemory();
    }

    @Test
    void whenGetAturnWithCorrectId_thenRightTurnIsReturned() {
        UUID idTurn1 = setTurnMock1();

        turnRepository.save(turn1);

        assertNotNull(turnRepository.findById(idTurn1));
        assertEquals(turn1.getId(), turnRepository.findById(idTurn1).getId());
    }

    @Test
    void quandRecupereUnTourAvecLebonId_alorsBonTourEstRetourner() {
        UUID idTurn1 = setTurnMock1();

        turnRepository.save(turn1);

        assertEquals(turn1, turnRepository.findById(idTurn1));
    }

    @Test
    void whenGetOneTurnWithTheBadId_thenNoneTurnIsReturned() {
        UUID idTurn1 = setTurnMock1();

        turnRepository.save(turn1);

        assertNull(turnRepository.findById(fakeTurnId));
    }

    @Test
    void repoContainsTwoTurns_whenResetTurns_thenRepoContainsNoMoreTurn() {
        UUID idTurn1 = setTurnMock1();
        UUID idTurn2 = setTurnMock2();

        turnRepository.save(turn1);
        turnRepository.save(turn2);

        turnRepository.reset();

        assertNotEquals(turn1, turnRepository.findById(idTurn1));
        assertNotEquals(turn2, turnRepository.findById(idTurn2));
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