package ca.ulaval.glo4002.game.domain.dinosaur;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.HERBIVORE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class HerdTest {
    public static final DietStrategyForHerbivore DIET_STRATEGY_FOR_HERBIVORE = new DietStrategyForHerbivore(HERBIVORE);
    private final Dinosaur firstDino = new Dinosaur("aaa", 100, "f", DIET_STRATEGY_FOR_HERBIVORE, "Ankylosaurus");
    private final Dinosaur secondDino = new Dinosaur("ab", 100, "f", DIET_STRATEGY_FOR_HERBIVORE, "Ankylosaurus");
    private final Dinosaur thirdDino = new Dinosaur("a", 50, "m", DIET_STRATEGY_FOR_HERBIVORE, "Ankylosaurus");
    private final Dinosaur lastDino = new Dinosaur("aa", 1, "f", DIET_STRATEGY_FOR_HERBIVORE, "Ankylosaurus");
    private Herd herd;
    private List<Dinosaur> dinoInDisorderList;

    @BeforeEach
    void setup() {
        herd = new Herd();
        setHerdWithDinos();
        resourcesStateDtoMock = mock(ResourcesStateDto.class);
    }

    @Test
    void givenUnorderedDinos_whenSortingDinosaurs_thenCorrectlySortByStrengthThenName() {

        List<Dinosaur> sortedDinosaurs = herd.findSortedDinosaursByStrengthThenName();

        List<Dinosaur> dinoInStrengthOrderList = createDinoInStrengthOrderList();
        assertArrayEquals(dinoInStrengthOrderList.toArray(), sortedDinosaurs.toArray());
    }

    @Test
    void whenFindAll_thenGetAllDinos() {

        assertEquals(dinoInDisorderList.size(), herd.findAllDinosaurs().size());
    }

    @Test
    void whenFindByName_thenGetDinoWithTheRightName() {

        assertEquals(lastDino, herd.findDinosaurByName(lastDino.getName()));
    }

    @Test
    void dinoHasBeenRemoved_whenFindByName_thenCanNotGetDino() {

        herd.removeDinosaur(firstDino);

        assertNull(herd.findDinosaurByName(firstDino.getName()));
        assertNotEquals(firstDino, herd.findDinosaurByName(firstDino.getName()));
    }

    private void setHerdWithDinos() {
        dinoInDisorderList = createDinoInDisorderList();
        for (Dinosaur dino : dinoInDisorderList) {
            herd.addDinosaur(dino);
        }
    }

    private List<Dinosaur> createDinoInStrengthOrderList() {
        return Arrays.asList(firstDino, secondDino, thirdDino, lastDino);
    }

    private List<Dinosaur> createDinoInDisorderList() {
        return Arrays.asList(lastDino, secondDino, thirdDino, firstDino);
    }
}