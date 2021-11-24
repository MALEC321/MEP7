package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class PantryTest {

    Pantry pantry;

    ResourcesGroup resourcesGroup = new ResourcesGroup();

    ResourcesFactory resourcesFactory = new ResourcesFactory();

    @BeforeEach
    public void createPantry() {
        pantry = new Pantry();
        pantry.addResources(resourcesFactory.create(BURGER, 50));
        pantry.addResources(resourcesFactory.create(SALAD, 400));
        pantry.addResources(resourcesFactory.create(WATER, 1000));
    }

    //TODO getFreshResourcesReport

    //TODO findFreshResources

//    @Test
//    public void givenAPopulatedPantry_whenFindingFreshResourcesGroup_thenFreshResourcesAreAddedInResourceGroup() {
//        ResourcesGroup freshResources = pantry.findFreshResourcesGroup();
//
//        assertEquals(50, freshResources.getResourceQuantity(BURGER));
//        assertEquals(400, freshResources.getResourceQuantity(SALAD));
//        assertEquals(1000, freshResources.getResourceQuantity(WATER));
//    }


//    @Test
//    public void givenAPopulatedPantry_whenAddingResources_thenIncrementFreshResourcesQty() {
//        pantry.addResources(resourcesFactory.create(BURGER, 50));
//
//        assertEquals(100, pantry.findFreshResourcesGroup().getResourceQuantity(BURGER));
//    }

//    @Test
//    public void givenAPopulatedPantry_whenRemovingResourceQty_thenDecrementFreshResourcesQty() {
//        pantry.removeResourceQty(BURGER, 25);
//
//        assertEquals(25, pantry.findFreshResourcesGroup().getResourceQuantity(BURGER));
//    }

    //TODO removeQuantity

//    @Test
//    public void givenAPopulatedPantry_whenRemovingResourcesQty_thenReturnTrueIfRessourcesRemoved() {
//        boolean removeRessouces = pantry.removeResourceQty(BURGER, 25);
//
//        assertTrue(removeRessouces);
//    }
//
//    @Test
//    public void givenAPopulatedPantry_whenRemovingMoreResourcesThanPossible_thenReturnFalse() {
//        boolean removeRessouces = pantry.removeResourceQty(BURGER, 100);
//
//        assertFalse(removeRessouces);
//    }
//
//    @Test
//    public void givenAPopulatedPantry_whenRemovingResourceQty_thenIncrementConsumedResourcesQty() {
//        pantry.removeResourceQty(BURGER, 25);
//
//        assertEquals(25, pantry.getConsumedResourcesGroup().getResourceQuantity(BURGER));
//    }
//
//    @Test
//    public void givenAPopulatedPantry_whenRemovingMoreResourcesThanPossible_thenIncrementConsumedResourcesQty() {
//        pantry.removeResourceQty(BURGER, 100);
//
//        assertEquals(50, pantry.getConsumedResourcesGroup().getResourceQuantity(BURGER));
//    }
//
//    @Test
//    public void givenAPopulatedPantry_whenRemovingAllExpiredResources_thenAllEmptyResourcesShouldBeRemove(){
//        pantry.removeResourceQty(BURGER, 50);
//        pantry.removeAllExpiredResources();
//
//        assertTrue(pantry.getFreshResources().get(BURGER).isEmpty());
//    }

//    @Test
//    public void givenAPopulatedPantry_whenRemovingAllExpiredResources_thenAllExpiredShouldBeInExpiredResources(){
//        pantry.decreaseExpirationDate();
//        pantry.decreaseExpirationDate();
//        pantry.decreaseExpirationDate();
//        pantry.removeAllExpiredResources();
//
//        assertEquals(400, pantry.getExpiredResourcesGroup().getResourceQuantity(SALAD));
//    }

//    @Test
//    public void givenAPopulatedPantry_whenDecreasingExpirationDate_thenExpirationDateIsDecreaseByOne(){
//        pantry.decreaseExpirationDate();
//
//        assertEquals(9, pantry.getFreshResources().get(BURGER).forEach(resources -> resources.getExpiration()));
//    }
}