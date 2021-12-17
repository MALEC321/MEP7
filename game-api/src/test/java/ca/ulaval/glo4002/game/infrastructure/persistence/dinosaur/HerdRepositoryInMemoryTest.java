package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HerdRepositoryInMemoryTest {
    @InjectMocks
    private HerdRepositoryInMemory herdRepositoryInMemory;

    @Mock
    private Herd herd;

    @Mock
    private List<Herd> herds;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void repoContainingHerd_whenFindHerd_thenReturnedHerd() {

        assertEquals(this.herd, this.herdRepositoryInMemory.findCurrentHerd());
    }

    @Test
    public void givenHerd_whenSave_thenHerdsSizeIsIncremented() {
        int herdSize = herds.size();
        this.herdRepositoryInMemory.save(herd);
        assertEquals(herdSize+1, herds);
    }

    @Test
    public void whenFindByName_thenGetDinoWithTheRightName() {

        this.herdRepositoryInMemory.deleteAll();

        verify(this.herd, times(1)).clear();
    }
}