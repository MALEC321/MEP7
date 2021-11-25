package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DietStrategyFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class HerdTest {
    private Herd herd;
    private Dinosaur firstDino;
    private Dinosaur secondDino;
    private Dinosaur thirdDino;
    private Dinosaur lastDino;
    private DietStrategyFactory dietStrategyFactory;
    private List<Dinosaur> dinoInDisorderList;

    @Mock
    private Dinosaur firstDinoMock;

    @Mock
    private Dinosaur secondDinoMock;

    @BeforeEach
    void setup() {
        herd = new Herd();
        dietStrategyFactory = new DietStrategyFactory();

        DietStrategy dietStrategy = dietStrategyFactory.create(DietType.HERBIVORE.name());
        firstDino = new Dinosaur("aaa", 100, "f", dietStrategy, "Ankylosaurus");
        secondDino = new Dinosaur("ab", 100, "f", dietStrategy, "Ankylosaurus");
        thirdDino = new Dinosaur("a", 50, "m", dietStrategy, "Ankylosaurus");
        lastDino = new Dinosaur("aa", 1, "f", dietStrategy, "Ankylosaurus");

        firstDinoMock = mock(Dinosaur.class);
        secondDinoMock = mock(Dinosaur.class);
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
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengerWins_thenChallengerIsHungry() {
        herd.fight(firstDino, thirdDino, true);
        assertTrue(firstDino.isHungry());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengerWins_thenChallengeeIsDead() {
        herd.fight(firstDino, thirdDino, true);
        assertTrue(thirdDino.isDead());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengerWins_thenReturnsNameOfChallenger() {
        String winner = herd.fight(firstDino, thirdDino, true);
        assertEquals(winner, firstDino.getName());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengeeWins_thenChallengeeIsHungry() {
        herd.fight(lastDino, secondDino, true);
        assertTrue(secondDino.isHungry());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengeeWins_thenChallengerIsDead() {
        herd.fight(lastDino, secondDino, true);
        assertTrue(lastDino.isDead());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForRealAndChallengeeWins_thenReturnsNameOfChallengee() {
        String winner = herd.fight(lastDino, secondDino, true);
        assertEquals(winner, secondDino.getName());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingNotForRealAndChallengerWins_thenChallengeeIsNotDead() {
        herd.fight(firstDino, thirdDino, false);
        assertFalse(thirdDino.isDead());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingNotForRealAndChallengerWins_thenReturnsNameOfChallenger() {
        String winner = herd.fight(firstDino, thirdDino, false);
        assertEquals(winner, firstDino.getName());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingNotForRealAndChallengeeWins_thenChallengeeSetHungryIsNotCalled() {
        herd.fight(firstDinoMock, secondDinoMock, false);
        when(firstDinoMock.getStrength()).thenReturn(10);
        when(secondDinoMock.getStrength()).thenReturn(100);
        verify(firstDinoMock, never()).setHungry(true);
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenFightingForNotRealAndChallengeeWins_thenChallengerIsNotDead() {
        herd.fight(lastDino, secondDino, false);
        assertFalse(lastDino.isDead());
    }

    @Test
    void givenTwoDinosaursOfDifferentWeight_whenNotFightingForRealAndChallengeeWins_thenReturnsNameOfChallengee() {
        String winner = herd.fight(lastDino, secondDino, false);
        assertEquals(winner, secondDino.getName());
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenFightingForReal_thenChallengerIsHungry() {
        herd.fight(firstDino, secondDino, true);
        assertTrue(firstDino.isHungry());
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenFightingForReal_thenStringTieIsReturned() {
        String winner;
        winner = herd.fight(firstDino, secondDino, true);
        assertEquals(winner, "tie");
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenFightingForReal_thenChallengeeIsHungry() {
        herd.fight(firstDino, secondDino, true);
        assertTrue(secondDino.isHungry());
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenNotFightingForReal_thenDoNotSetChallengerHungry() {

        herd.fight(firstDinoMock, secondDinoMock, false);

        when(firstDinoMock.getStrength()).thenReturn(100);
        when(secondDinoMock.getStrength()).thenReturn(10);
        verify(firstDinoMock, never()).setHungry(true);
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenNotFightingForReal_thenStringTieIsReturned() {
        String winner;
        winner = herd.fight(firstDino, secondDino, false);
        assertEquals(winner, "tie");
    }

    @Test
    void givenTwoDinosaursOfIdenticalWeight_whenNotFightingForReal_thenChallengeeIsNotHungry() {
        herd.fight(firstDinoMock, secondDinoMock, false);
        when(firstDinoMock.getStrength()).thenReturn(10);
        when(secondDinoMock.getStrength()).thenReturn(100);
        verify(secondDinoMock, never()).setHungry(true);
    }

    private List<Dinosaur> createDinoInStrengthOrderList() {
        return Arrays.asList(firstDino, secondDino, thirdDino, lastDino);
    }

    private List<Dinosaur> createDinoInDisorderList() {
        return Arrays.asList(lastDino, secondDino, thirdDino, firstDino);
    }
}