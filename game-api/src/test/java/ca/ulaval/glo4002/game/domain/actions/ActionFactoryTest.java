package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ActionFactoryTest {

    private DinosaurFactory dinosaurFactory;
    private Herd herd;
    private HerdRepositoryInMemory herdRepository;
    private SpeciesDietsCorrespondances speciesDietCorrespondances;
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTest3;
    private Dinosaur dinoTestTyrannosaurus;
    private Dinosaur dinoTest5;
    private Dinosaur dinoTest6;
    private Dinosaur dinoTest7;
    private Dinosaur dinoTest8;
    private ActionFactory actionFactory;
    List<Action> actions;

    @BeforeEach
    public void setUp() {
        herd = new Herd();
        herdRepository = new HerdRepositoryInMemory();
        speciesDietCorrespondances = new SpeciesDietsCorrespondances();
        dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
        dinoTest3 = dinosaurFactory.createDinosaur("Beno", 9, "m", "Diplodocus");
        dinoTestTyrannosaurus = dinosaurFactory.createDinosaur("Ariau", 11, "m", "Tyrannosaurus Rex");
        dinoTest5 = dinosaurFactory.createDinosaur("Maxime", 12, "m", "Megalosaurus");
        dinoTest6 = dinosaurFactory.createDinosaur("Miguel", 13, "m", "Spinosaurus");
        dinoTest7 = dinosaurFactory.createDinosaur("Debonnaire", 14, "m", "Spinosaurus");
        dinoTest8 = dinosaurFactory.createDinosaur("Christian", 15, "m", "Spinosaurus");
        actionFactory = new ActionFactory();
        actions = new ArrayList<>();

    }

    @Test
    void given2orMoreFightActions_whenCreatingAFight_thenThrowsMaxCombatReachedException() {
        Action action1 = mock(FightAction.class);
        Action action2 = mock(FightAction.class);
        actions.add(action1);
        actions.add(action2);

        assertThrows(MaxCombatsReachedException.class, () ->
                actionFactory.validateNumberOfCurrentFights(actions));

    }

    @Test
    void givenDinosaursWithInvalidName_whenCreatingAFight_thenThrowsNotExistentNameException() {
        Dinosaur nonExistentDino1 = herd.findDinosaurByName("John");
        Dinosaur nonExistentDino2 = herd.findDinosaurByName("Jane");
        assertThrows(NotExistentNameException.class, () ->
                actionFactory.validateFighters(nonExistentDino1, nonExistentDino2));
    }

    @Test
    void givenTyrannosaurusRex_whenCreatingAFight_thenThrowsArmsTooShortException() {
        assertThrows(ArmsTooShortException.class, () ->
                actionFactory.validateFighters(dinoTest1, dinoTestTyrannosaurus));
    }

    @Test
    void givenDinosaurWhoIsAlreadyFighting_whenCreatingAFight_thenThrowsDinosaurAlreadyParticipatingException() {
        dinoTest1.setFighting(true);
        assertThrows(DinosaurAlreadyParticipatingException.class, () ->
                actionFactory.validateFighters(dinoTest1, dinoTest2));
    }

    @Test
    void givenTwoDinosaurs_whenCreatingAFight_thenFightingIsTrue_AndNewFightingActionIsReturned() {
        Action fightingAction = actionFactory.createFight(actions, dinoTest1, dinoTest2, herd);
        assertEquals(dinoTest1.isFighting(), true);
        assertEquals(dinoTest2.isFighting(), true);
    }
}