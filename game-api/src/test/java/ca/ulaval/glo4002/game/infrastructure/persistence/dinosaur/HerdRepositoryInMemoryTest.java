package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class HerdRepositoryInMemoryTest {
    @InjectMocks
    private HerdRepositoryInMemory herdRepositoryInMemory;

    //    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus", );
//    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus", );
//    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus", );
//    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus", );
    @Mock
    private Herd herd;


    @BeforeEach
    void setup() {
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