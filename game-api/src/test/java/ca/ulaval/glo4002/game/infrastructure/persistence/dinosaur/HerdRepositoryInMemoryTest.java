package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import org.junit.Test;;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

public class HerdRepositoryInMemoryTest {

    private HerdRepositoryInMemory herdRepositoryInMemory;

    private Herd herd;

    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");

    private List<Dinosaur> dinoInDisorderList;
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        herdRepositoryInMemory = new HerdRepositoryInMemory();

        herdRepositoryInMemory.findHerd().addDinosaur(firstDino);
        herdRepositoryInMemory.findHerd().addDinosaur(secondDino);
        herdRepositoryInMemory.findHerd().addDinosaur(thirdDino);

//        herdRepositoryInMemory.deleteAll();
//        herdRepositoryInMemory.save(herd);

    }

    @Test
    public void repoContainingHerd_whenFindHerd_thenReturnedHerd() {

        assertEquals(this.herd, this.herdRepositoryInMemory.findHerd());
    }

    @Test
    public void givenHerd_whenSave_thenReturnedNotEmptyHerds() {

        herd = new Herd();

        herdRepositoryInMemory.save(herd);

        assertNotEquals(0, dinoInDisorderList.size());
    }

    @Test
    public void whenFindByName_thenGetDinoWithTheRightName() {

        herdRepositoryInMemory.deleteAll();

       verify(herd).clear();
    }
}