package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HerdTest {
    private Herd herd;

    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");

    private List<Dinosaur> dinoInDisorderList;

    @BeforeEach
    void setup() {
        herd = new Herd();
    }

    @Test
    void givenUnorderedDinos_whenSortingDinosaurs_thenCorrectlySortByStrengthThenName() {
        this.dinoInDisorderList = createDinoInDisorderList();
        for (Dinosaur dino : this.dinoInDisorderList) {
            herd.addDinosaur(dino);
        }

        List<Dinosaur> sortedDinosaurs = herd.findSortedDinosaursByStrengthThenName();

        List<Dinosaur> dinoInStrengthOrderList = createDinoInStrengthOrderList();
        assertArrayEquals(dinoInStrengthOrderList.toArray(), sortedDinosaurs.toArray());
    }

    private List<Dinosaur> createDinoInStrengthOrderList() {
        return Arrays.asList(firstDino, secondDino, thirdDino, lastDino);
    }

    private List<Dinosaur> createDinoInDisorderList() {
        return Arrays.asList(lastDino, secondDino, thirdDino, firstDino);
    }

/*    @Test
    void whenFindAll_thenGetAllDinos() {
        setRepoWithDinos();

        assertEquals(dinoInDisorderList.size(), herd.findAll().size());
    }

    @Test
    void whenFindByName_thenGetDinoWithTheRightName() {
        setRepoWithDinos();

        assertEquals(lastDino, herd.findByName(lastDino.getName()));
    }

    @Test
    void dinoHasBeenRemoved_whenFindByName_thenCanNotGetDino() {
        setRepoWithDinos();

        herd.remove(firstDino);

        assertNull(herd.findByName(firstDino.getName()));
        assertNotEquals(firstDino, herd.findByName(firstDino.getName()));
    }

    private void setRepoWithDinos() {
        this.dinoInDisorderList = createDinoInDisorderList();

        for (Dinosaur dino : this.dinoInDisorderList) {
            herd.save(dino);
        }
    }*/
}