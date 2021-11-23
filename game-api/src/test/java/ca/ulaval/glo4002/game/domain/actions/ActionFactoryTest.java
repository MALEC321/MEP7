package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionFactoryTest {

    @InjectMocks
    ActionFactory actionFactory;

    @Mock
    Dinosaur dinosaur;

    @Mock
    Herd herd;

    @Mock
    Resources resources;

    @Mock
    Pantry pantry;

    @Mock
    ResourcesFactory resourcesFactory;

    @Mock
    HerdRepository herdRepository;

    @BeforeEach
    void init() {
        actionFactory = new ActionFactory();
        MockitoAnnotations.initMocks(this);
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