package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class GameRepositoryInMemoryTest {
    @InjectMocks
    private GameRepositoryInMemory gameRepositoryInMemory;

    @Mock
    private Game game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenNoGame_whenFindCurrentGame_thenReturnsGame() {
        assertNotNull(gameRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenGame_whenSave_thenFindCurrentGameReturnsSavedGame() {
        gameRepositoryInMemory.save(game);
        assertEquals(game, gameRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenGame_whenResetCurrent_thenCurrentGameIsReset() {
        gameRepositoryInMemory.save(game);
        gameRepositoryInMemory.deleteAll();

        assertNotEquals(game, gameRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenNoGame_whenSave_thenFindPreviousGameThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> gameRepositoryInMemory.findPrevious());
    }

    @Test
    public void givenOneGames_whenSave_thenFindPreviousGameReturnsGame() {
        gameRepositoryInMemory.save(game);
        assertNotNull(gameRepositoryInMemory.findPrevious());
    }
}