package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

public class ResourceFactory {
    public Resources create(int qtyBurger, int qtySalad, int qtyWater) {
        if (qtyBurger < 0 || qtySalad < 0 || qtyWater < 0) {
            throw new InvalidResourceQuantityException();
        }

        return new Resources(new Burger(qtyBurger), new Salad(qtySalad), new Water(qtyWater));
    }
}
