package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HerdTest {
    private Herd herd;

    private Dinosaur firstDino;
    private Dinosaur secondDino;
    private Dinosaur thirdDino;
    private Dinosaur lastDino;

    private List<Dinosaur> dinoInDisorderList;

    @BeforeEach
    void setup() {
        herd = new Herd();
        firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
        secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
        thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
        lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");
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

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFighting_thenHeaviestIsHungryAndLightestIsDead() {
        herd.fight(firstDino, thirdDino, true);
        assertEquals(firstDino.isHungry(), true);
        assertEquals(thirdDino.isDead(), true);
        herd.fight(lastDino, secondDino, true);
        assertEquals(secondDino.isHungry(), true);
        assertEquals(lastDino.isDead(), true);
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenFighting_thenBothAreHungry() {
        herd.fight(firstDino, secondDino, true);
        assertEquals(firstDino.isHungry(), true);
        assertEquals(secondDino.isHungry(), true);
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