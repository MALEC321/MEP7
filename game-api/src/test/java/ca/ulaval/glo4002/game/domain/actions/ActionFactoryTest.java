package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;
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
        CookItAction cookItAction = actionFactory.createCookItAction(pantry, resourcesFactory);

        assertEquals(cookItAction.getPantry(), pantry);
    }

    @Test
    void givenAnActionFactory_whenCreatingCookItAction_thenReferencesToTheResourcesFactory() {
        CookItAction cookItAction = actionFactory.createCookItAction(pantry, resourcesFactory);

        assertEquals(cookItAction.getResourcesFactory(), resourcesFactory);
    }

    @Test
    void givenAnActionFactory_whenCreatingRemoveAllExpiredResourcesAction_thenReferencesToThePantry() {
        RemoveAllExpiredResourcesAction removeAllExpiredResourcesAction = actionFactory.createRemoveAllExpiredResourcesAction(pantry);

        assertEquals(removeAllExpiredResourcesAction.getPantry(), pantry);
    }

    @Test
    void givenAnActionFactory_whenCreatingRemoveAllEmptyResourcesAction_thenReferencesToThePantry() {
        RemoveAllEmptyResourcesAction removeAllEmptyResourcesAction = actionFactory.createRemoveAllEmptyResourcesAction(pantry);

        assertEquals(removeAllEmptyResourcesAction.getPantry(), pantry);
    }

    @Test
    void givenAnActionFactory_whenCreatingRemoveOrphanedBabyDinosaursAction_thenReferencesToTheHerd() {
        RemoveOrphanedBabyDinosaursAction removeOrphanedBabyDinosaursAction = actionFactory.createRemoveOrphanedBabyDinosaursAction(herd);

        assertEquals(removeOrphanedBabyDinosaursAction.getHerd(), herd);
    }
}