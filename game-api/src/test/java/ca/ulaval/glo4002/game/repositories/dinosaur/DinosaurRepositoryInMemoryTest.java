package ca.ulaval.glo4002.game.repositories.dinosaur;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

class DinosaurRepositoryInMemoryTest {

/*    private DinosaurRepositoryInMemory dinosaurRepositoryInMemory;

    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");

    private List<Dinosaur> dinoInDisorderList;

    @BeforeEach
    void setup() {
        dinosaurRepositoryInMemory = new DinosaurRepositoryInMemory();
    }

    @Test
    void whenSortingDinosaurs_thenCorrectlySortByStrengthThenName() {
        this.dinoInDisorderList = createDinoInDisorderList();
        List<Dinosaur> dinoInStrengthOrderList = createDinoInStrengthOrderList();
        for (Dinosaur dino : this.dinoInDisorderList) {
            dinosaurRepositoryInMemory.save(dino);
        }

        List<Dinosaur> sortedDinosaurs = dinosaurRepositoryInMemory.getSortedDinosaursByStrengthThenName();

        assertArrayEquals(dinoInStrengthOrderList.toArray(), sortedDinosaurs.toArray());
    }

    @Test
    void repoContainingDinos_whenResetRepo_thenRepoIsEmpty() {
        setRepoWithDinos();

        dinosaurRepositoryInMemory.reset();

        assertTrue(dinosaurRepositoryInMemory.findAll().isEmpty());
    }

    @Test
    void whenFindAll_thenGetAllDinosInRepo() {
        setRepoWithDinos();

        assertEquals(dinoInDisorderList.size(), dinosaurRepositoryInMemory.findAll().size());
    }

    @Test
    void whenFindByName_thenGetDinoWithTheRightName() {
        setRepoWithDinos();

        assertEquals(lastDino, dinosaurRepositoryInMemory.findByName(lastDino.getName()));
    }

    @Test
    void dinoHasBeenRemoved_whenFindByName_thenCanNotGetDino() {
        setRepoWithDinos();

        dinosaurRepositoryInMemory.remove(firstDino);

        assertNull(dinosaurRepositoryInMemory.findByName(firstDino.getName()));
        assertNotEquals(firstDino, dinosaurRepositoryInMemory.findByName(firstDino.getName()));
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
            dinosaurRepositoryInMemory.save(dino);
        }
    }*/

}