package ca.ulaval.glo4002.game.repositories.dinosaur;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public class HerdRepositoryInMemoryTest {
    private HerdRepositoryInMemory herdRepositoryInMemory;

    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");

    private List<Dinosaur> dinoInDisorderList;

    @BeforeEach
    void setup() {
        herdRepositoryInMemory = new HerdRepositoryInMemory();
    }

/*    @Test
    void repoContainingDinos_whenResetRepo_thenRepoIsEmpty() {
        setRepoWithDinos();

        herdRepositoryInMemory.reset();

        assertTrue(herdRepositoryInMemory.findAll().isEmpty());
    }

    @Test
    void whenFindAll_thenGetAllDinosInRepo() {
        setRepoWithDinos();

        assertEquals(dinoInDisorderList.size(), herdRepositoryInMemory.findAll().size());
    }

    @Test
    void whenFindByName_thenGetDinoWithTheRightName() {
        setRepoWithDinos();

        assertEquals(lastDino, herdRepositoryInMemory.findByName(lastDino.getName()));
    }

    @Test
    void dinoHasBeenRemoved_whenFindByName_thenCanNotGetDino() {
        setRepoWithDinos();

        herdRepositoryInMemory.remove(firstDino);

        assertNull(herdRepositoryInMemory.findByName(firstDino.getName()));
        assertNotEquals(firstDino, herdRepositoryInMemory.findByName(firstDino.getName()));
    }

    private List<Dinosaur> createDinoInStrengthOrderList() {
        return Arrays.asList(firstDino, secondDino, thirdDino, lastDino);
    }

    private List<Dinosaur> createDinoInDisorderList() {
        return Arrays.asList(lastDino, secondDino, thirdDino, firstDino);
    }

    private void setRepoWithDinos() {
        this.dinoInDisorderList = createDinoInDisorderList();

        for (Dinosaur dino : this.dinoInDisorderList) {
            herdRepositoryInMemory.save(dino);
        }
    }*/
}