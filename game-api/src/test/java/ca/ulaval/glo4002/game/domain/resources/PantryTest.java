package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.ResourceTypeQuantity;
import ca.ulaval.glo4002.game.domain.dinosaur.ResourcesStateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PantryTest {
    private Pantry pantry;
    private ResourcesFactory resourcesFactory;
    private final List<ResourceTypeQuantity> resourceTypeQuantityList = new ArrayList<>();

    @BeforeEach
    public void createPantry() {
        pantry = new Pantry();
        resourcesFactory = new ResourcesFactory();
        pantry.addResources(resourcesFactory.create(BURGER, 50));
        pantry.addResources(resourcesFactory.create(SALAD, 400));
        pantry.addResources(resourcesFactory.create(WATER, 1000));
    }

    @Test
    public void givenAPopulatedPantry_whenGettingFreshResourcesReport_thenFreshRessourcesAreAddedToResourcesStateDto(){
        ResourcesStateDto resourcesStateDto = pantry.getFreshResourcesReport();

        assertEquals(resourcesStateDto.getQtyForResourceType(BURGER), pantry.getFreshResources().get(BURGER).element().getQuantity());
    }

    @Test
    public void givenAPopulatedPantry_whenFindingFreshResourcesGroup_thenFreshResourcesAreAddedInResourceGroup() {
        ResourcesGroup resourcesGroup = pantry.findFreshResourcesGroup();

        assertEquals(50, resourcesGroup.getResourceQuantity(BURGER));
        assertEquals(400, resourcesGroup.getResourceQuantity(SALAD));
        assertEquals(1000, resourcesGroup.getResourceQuantity(WATER));
    }

    @Test
    public void givenAPopulatedPantry_whenAddingResources_thenIncrementFreshResourcesQty() {
        Resources resources = resourcesFactory.create(BURGER, 50);
        pantry.addResources(resources);

        assertEquals(100, pantry.findFreshResourcesGroup().getResourceQuantity(BURGER));
    }

    @Test
    public void givenAnUpdatedResourcesStateDto_whenUpdatingQuantities_thenResourcesAreConsumed(){
        ResourceTypeQuantity resourceTypeQuantity = new ResourceTypeQuantity(BURGER, 25);
        resourceTypeQuantityList.add(resourceTypeQuantity);
        ResourcesStateDto resourcesStateDto = new ResourcesStateDto(resourceTypeQuantityList);

        pantry.updateQuantities(resourcesStateDto);

        assertEquals(25, pantry.getConsumedResourcesGroup().getResourceQuantity(BURGER));
    }

    @Test
    public void givenAPopulatedPantry_whenRemovingAllExpiredResources_thenAllExpiredResourcesShouldBeInExpiredResources(){
        pantry.decreaseExpirationDate();
        pantry.decreaseExpirationDate();
        pantry.decreaseExpirationDate();
        pantry.removeAllExpiredResources();

        assertEquals(400, pantry.getExpiredResourcesGroup().getResourceQuantity(SALAD));
    }

    @Test
    public void givenAPopulatedPantry_whenDecreasingExpirationDate_thenResourceElementExpirationIsDecreaseByOne(){
        pantry.decreaseExpirationDate();

        assertEquals(3, pantry.getFreshResources().get(BURGER).element().getExpiration());
    }

    @Test
    public void givenAPopulatedPantry_whenClearing_thenFreshResourcesAreClear(){
        pantry.clear();

        assertEquals(0, pantry.findFreshResourcesGroup().getResourceQuantity(BURGER));
        assertEquals(0, pantry.findFreshResourcesGroup().getResourceQuantity(SALAD));
        assertEquals(0, pantry.findFreshResourcesGroup().getResourceQuantity(WATER));
    }

    @Test
    public void givenAnUpdatedPantry_whenClearing_thenConsumedResourcesAreClear(){
        ResourceTypeQuantity resourceTypeQuantity = new ResourceTypeQuantity(BURGER, 25);
        resourceTypeQuantityList.add(resourceTypeQuantity);
        ResourcesStateDto resourcesStateDto = new ResourcesStateDto(resourceTypeQuantityList);
        pantry.updateQuantities(resourcesStateDto);

        pantry.clear();

        assertEquals(0, pantry.getConsumedResourcesGroup().getResourceQuantity(BURGER));
        assertEquals(0, pantry.getConsumedResourcesGroup().getResourceQuantity(SALAD));
        assertEquals(0, pantry.getConsumedResourcesGroup().getResourceQuantity(WATER));
    }

    @Test
    public void givenAPopulatedPantry_whenClearing_thenExpiredResourcesAreClear(){
        pantry.decreaseExpirationDate();
        pantry.decreaseExpirationDate();
        pantry.decreaseExpirationDate();
        pantry.clear();

        assertEquals(0, pantry.getExpiredResourcesGroup().getResourceQuantity(BURGER));
        assertEquals(0, pantry.getExpiredResourcesGroup().getResourceQuantity(SALAD));
        assertEquals(0, pantry.getExpiredResourcesGroup().getResourceQuantity(WATER));
    }
}