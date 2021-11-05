package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

public class ResourceGroupFactory {
    public ResourceGroup create(int qtyBurger, int qtySalad, int qtyWater) {
        if (qtyBurger < 0 || qtySalad < 0 || qtyWater < 0) {
            throw new InvalidResourceQuantityException();
        }

        return new ResourceGroup(qtyBurger, qtySalad, qtyWater);
    }
}
