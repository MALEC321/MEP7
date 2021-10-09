package ca.ulaval.glo4002.game.repositories.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurRepositoryInMemoryTest {
    DinosaurRepositoryInMemory dinosaurRepositoryInMemory;

    @BeforeEach
    void setup() {
        dinosaurRepositoryInMemory = new DinosaurRepositoryInMemory();
    }

    @Test
    void whenSortingDinosaurs_thenCorrectlySortByStrengthThenName() {
        Dinosaur firstDino = new Dinosaur("aaa", 100, "f", "Ankylosaurus");
        Dinosaur secondDino = new Dinosaur("ab", 100, "f", "Ankylosaurus");
        Dinosaur thirdDino = new Dinosaur("a", 50, "m", "Ankylosaurus");
        Dinosaur lastDino = new Dinosaur("aa", 1, "f", "Ankylosaurus");
        dinosaurRepositoryInMemory.save(secondDino);
        dinosaurRepositoryInMemory.save(lastDino);
        dinosaurRepositoryInMemory.save(firstDino);
        dinosaurRepositoryInMemory.save(thirdDino);

        List<Dinosaur> sortedDinosaurs = dinosaurRepositoryInMemory.getSortedDinosaursByStrengthThenName();

        List<Dinosaur> dinoListInOrder = new ArrayList<>();
        dinoListInOrder.add(firstDino);
        dinoListInOrder.add(secondDino);
        dinoListInOrder.add(thirdDino);
        dinoListInOrder.add(lastDino);
        assertEquals(dinoListInOrder, sortedDinosaurs);
    }
}