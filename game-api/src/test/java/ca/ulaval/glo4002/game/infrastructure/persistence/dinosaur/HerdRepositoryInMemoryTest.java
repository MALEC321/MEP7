package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HerdRepositoryInMemoryTest {
    @InjectMocks
    private HerdRepositoryInMemory herdRepositoryInMemory;

    @Mock
    List<Herd> herds;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenZeroHerd_whenSave_thenFindCurrentHerdThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> herdRepositoryInMemory.findCurrentHerd());
    }

    @Test
    public void givenZeroHerd_whenSave_thenFindPreviousHerdThrowsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> herdRepositoryInMemory.findCurrentHerd());
    }

    @Test
    public void givenOneHerd_whenSave_thenFindCurrentHerdReturnsHerd() {
        herdRepositoryInMemory.save(new Herd());
        assertNotNull(herdRepositoryInMemory.findCurrentHerd());
    }

    @Test
    public void givenOneHerd_whenSave_thenFindPreviousHerdThrowsException() {
        herdRepositoryInMemory.save(new Herd());
        assertThrows(IndexOutOfBoundsException.class, () -> herdRepositoryInMemory.findPreviousHerd());
    }

    @Test
    public void givenTwoHerds_whenSave_thenFindPreviousHerdReturnsHerd() {
        herdRepositoryInMemory.save(new Herd());
        herdRepositoryInMemory.save(new Herd());
        assertNotNull(herdRepositoryInMemory.findPreviousHerd());
    }

    @Test
    public void givenNothing_whenDeleteAll_thenFindCurrentHerdThrowsException() {
        this.herdRepositoryInMemory.deleteAll();
        assertThrows(IndexOutOfBoundsException.class, () -> herdRepositoryInMemory.findPreviousHerd());
    }
}