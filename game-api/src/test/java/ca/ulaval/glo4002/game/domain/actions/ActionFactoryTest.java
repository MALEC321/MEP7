package ca.ulaval.glo4002.game.domain.actions;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;

public class ActionFactoryTest {

    @Mock
    HerdRepository herdRepository;
    List<Action> actions;
    @InjectMocks
    private ActionFactory actionFactory;
    @Mock
    private Dinosaur dinosaur;
    @Spy
    private Herd herd;
    @Mock
    private Resources resources;
    @Mock
    private Pantry pantry;
    @Mock
    private ResourcesFactory resourcesFactory;
    private ResourcesDistributor resourcesDistributor;
    private DinosaurFactory dinosaurFactory;
    private SpeciesDietsCorrespondances speciesDietCorrespondances;
    private Dinosaur dinoTest1;
    private Dinosaur dinoTest2;
    private Dinosaur dinoTestTyrannosaurus;

    @BeforeEach
    public void setUp() {
        actionFactory = new ActionFactory();
        MockitoAnnotations.initMocks(this);
        herd = new Herd();
        herdRepository = new HerdRepositoryInMemory();
        speciesDietCorrespondances = new SpeciesDietsCorrespondances();
        dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietCorrespondances);
        dinoTest1 = dinosaurFactory.createDinosaur("Maxence", 10, "m", "Ankylosaurus");
        dinoTest2 = dinosaurFactory.createDinosaur("Marc-Antoine", 10, "m", "Brachiosaurus");
        dinoTestTyrannosaurus = dinosaurFactory.createDinosaur("Ariau", 11, "m", "Tyrannosaurus Rex");
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
        assertTrue(dinoTest1.isFighting());
        assertTrue(dinoTest2.isFighting());
    }

    @Test
    void givenAnActionFactory_whenCreatingAddDinoAction_thenReferencesToTheDinosaur() {
        AddDino addDino = actionFactory.createAddDinoAction(dinosaur, herd);

        assertEquals(addDino.getDinosaur(), dinosaur);
    }

    @Test
    void givenAnActionFactory_whenCreatingAddDinoAction_thenReferencesToTheHerd() {
        AddDino addDino = actionFactory.createAddDinoAction(dinosaur, herd);

        assertEquals(addDino.getHerd(), herd);
    }

    @Test
    void givenAnActionFactory_whenCreatingAddResourceAction_thenReferencesToTheResources() {
        AddResource addResource = actionFactory.createAddResourceAction(resources, pantry);

        assertEquals(addResource.getResources(), resources);
    }

    @Test
    void givenAnActionFactory_whenCreatingAddResourceAction_thenReferencesToThePantry() {
        AddResource addResource = actionFactory.createAddResourceAction(resources, pantry);

        assertEquals(addResource.getPantry(), pantry);
    }

    @Test
    void givenAnActionFactory_whenCreatingCookItAction_thenReferencesToThePantry() {
        CookIt cookIt = actionFactory.createCookItAction(pantry, resourcesFactory);

        assertEquals(cookIt.getPantry(), pantry);
    }

    @Test
    void givenAnActionFactory_whenCreatingCookItAction_thenReferencesToTheResourcesFactory() {
        CookIt cookIt = actionFactory.createCookItAction(pantry, resourcesFactory);

        assertEquals(cookIt.getResourcesFactory(), resourcesFactory);
    }

    @Test
    void givenAnActionFactory_whenCreatingRemoveAllExpiredResourcesAction_thenReferencesToThePantry() {
        RemoveExpiredResources removeExpiredResources = actionFactory.createRemoveAllExpiredResourcesAction(pantry);

        assertEquals(removeExpiredResources.getPantry(), pantry);
    }

//    @Test
//    void givenAnActionFactory_whenCreatingFeedDinosaursAction_thenReferencesToTheResourcesDistributor() {
//        FeedDinosaurs feedDinosaurs = actionFactory.createFeedDinosaursAction(herdRepository, pantry, herd);
//
//        assertEquals(feedDinosaurs.getResourcesDistributor(), resourcesDistributor);
//    }
//
//    @Test
//    void givenAnActionFactory_whenCreatingFeedDinosaursAction_thenReferencesToThePantry() {
//        FeedDinosaurs feedDinosaurs = actionFactory.createFeedDinosaursAction(resourcesDistributor, pantry, herd);
//
//        assertEquals(feedDinosaurs.getPantry(), pantry);
//    }
//
//    @Test
//    void givenAnActionFactory_whenCreatingFeedDinosaursAction_thenReferencesToTheHerd() {
//        FeedDinosaurs feedDinosaurs = actionFactory.createFeedDinosaursAction(resourcesDistributor, pantry, herd);
//
//        assertEquals(feedDinosaurs.getHerd(), herd);
//    }

    @Test
    void givenAnActionFactory_whenCreatingRemoveOrphanedBabyDinosaursAction_thenReferencesToTheHerd() {
        RemoveOrphanedBabyDinosaurs removeOrphanedBabyDinosaurs = actionFactory.createRemoveOrphanedBabyDinosaursAction(herd);

        assertEquals(removeOrphanedBabyDinosaurs.getHerd(), herd);
    }
}