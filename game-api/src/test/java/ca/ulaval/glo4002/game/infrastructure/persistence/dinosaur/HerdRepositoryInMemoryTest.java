package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    public void repoContainingHerd_whenFindHerd_thenReturnedHerd() {

        Assertions.assertEquals(this.herd, this.herdRepositoryInMemory.findHerd());
    }

    @Test
    public void givenHerd_whenSave_thenReturnedNotEmptyHerds() {

        this.herdRepositoryInMemory.save(herd);

        Assertions.assertNotNull(this.herdRepositoryInMemory);
    }

    @Test
    public void whenFindByName_thenGetDinoWithTheRightName() {

        this.herdRepositoryInMemory.deleteAll();

        verify(this.herd, times(1)).clear();
    }
}