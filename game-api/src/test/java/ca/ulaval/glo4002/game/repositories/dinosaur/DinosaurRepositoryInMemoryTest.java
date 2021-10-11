package ca.ulaval.glo4002.game.repositories.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DinosaurRepositoryInMemoryTest {

    private DinosaurRepositoryInMemory dinosaurRepositoryInMemory;

    private Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
    private Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
    private Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
    private Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");

    @BeforeEach
    void setup() {
        dinosaurRepositoryInMemory = new DinosaurRepositoryInMemory();
    }

    @Test
    void whenSortingDinosaurs_thenCorrectlySortByStrengthThenName() {
        List<Dinosaur> dinoInDisorderList = createDinoInDisorderList();
        List<Dinosaur> dinoInStrengthOrderList = createDinoInStrengthOrderList();

        for (Dinosaur dino : dinoInDisorderList) {
            dinosaurRepositoryInMemory.save(dino);
        }

        List<Dinosaur> sortedDinosaurs = dinosaurRepositoryInMemory.getSortedDinosaursByStrengthThenName();

        assertArrayEquals(dinoInStrengthOrderList.toArray(), sortedDinosaurs.toArray());
    }


    private List<Dinosaur> createDinoInStrengthOrderList() {
        return Arrays.asList(firstDino, secondDino, thirdDino, lastDino);
    }

    private List<Dinosaur> createDinoInDisorderList() {
        return Arrays.asList(lastDino, secondDino, thirdDino, firstDino);
    }


}