package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourcesGroupFactory {
    public ResourcesGroup create(int qtyBurger, int qtySalad, int qtyWater) {
        ResourcesFactory resourcesFactory = new ResourcesFactory();

        Resources burger, salad, water;
        burger = resourcesFactory.create(BURGER, qtyBurger);
        salad = resourcesFactory.create(SALAD, qtySalad);
        water = resourcesFactory.create(WATER, qtyWater);

        return new ResourcesGroup(burger, salad, water);
    }
}
