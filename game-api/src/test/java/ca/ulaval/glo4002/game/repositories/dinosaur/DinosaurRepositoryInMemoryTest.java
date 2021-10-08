package ca.ulaval.glo4002.game.repositories.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurAdult;
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
        Dinosaur firstDino = new DinosaurAdult("aaa", 100, "f", "Ankylosaurus");
        Dinosaur secondDino = new DinosaurAdult("ab", 100, "f", "Ankylosaurus");
        Dinosaur thirdDino = new DinosaurAdult("a", 50, "m", "Ankylosaurus");
        Dinosaur lastDino = new DinosaurAdult("aa", 1, "f", "Ankylosaurus");
        dinosaurRepositoryInMemory.save(secondDino);
        dinosaurRepositoryInMemory.save(lastDino);
        dinosaurRepositoryInMemory.save(firstDino);
        dinosaurRepositoryInMemory.save(thirdDino);
        List<Dinosaur> dinoListInOrder = new ArrayList<>();
        dinoListInOrder.add(firstDino);
        dinoListInOrder.add(secondDino);
        dinoListInOrder.add(thirdDino);
        dinoListInOrder.add(lastDino);

        assertEquals(dinoListInOrder, dinosaurRepositoryInMemory.getSortedDinosaursByStrengthThenName());
    }
}