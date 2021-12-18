package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class HerdRepositoryInMemoryTest {
    @InjectMocks
    private HerdRepositoryInMemory herdRepositoryInMemory;

    @Mock
    private Herd herd;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenNoHerd_whenFindCurrentHerd_thenReturnsHerd() {
        assertNotNull(herdRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenHerd_whenSave_thenFindCurrentHerdReturnsSavedHerd() {
        herdRepositoryInMemory.save(herd);
        assertEquals(herd, herdRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenHerd_whenResetCurrent_thenCurrentHerdIsReset() {
        herdRepositoryInMemory.save(herd);
        herdRepositoryInMemory.deleteAll();

        assertNotEquals(herd, herdRepositoryInMemory.findCurrent());
    }

    @Test
    public void givenNoHerd_whenSave_thenFindPreviousHerdThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> herdRepositoryInMemory.findPrevious());
    }

    @Test
    public void givenOneHerds_whenSave_thenFindPreviousHerdReturnsHerd() {
        herdRepositoryInMemory.save(herd);
        assertNotNull(herdRepositoryInMemory.findPrevious());
    }

}