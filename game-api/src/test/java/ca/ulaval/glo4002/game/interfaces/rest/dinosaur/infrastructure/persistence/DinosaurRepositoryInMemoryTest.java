package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurServices;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DinosaurRepositoryInMemoryTest {
    DinosaurServices dinosaurRepositoryInMemory;

    @BeforeEach
    void setup() {
        dinosaurRepositoryInMemory = new DinosaurServices();
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
        List<Dinosaur> dinoListInOrder = new ArrayList<>();
        dinoListInOrder.add(firstDino);
        dinoListInOrder.add(secondDino);
        dinoListInOrder.add(thirdDino);
        dinoListInOrder.add(lastDino);

        assertEquals(dinoListInOrder, dinosaurRepositoryInMemory.getSortedDinosaursByStrengthThenName());
    }
}