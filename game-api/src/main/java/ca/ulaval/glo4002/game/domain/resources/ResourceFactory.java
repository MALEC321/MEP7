package ca.ulaval.glo4002.game.domain.resources;

public class ResourceFactory {
    public Resource create(int qtyBurger, int qtySalad, int qtyWater) {
        if (qtyBurger < 0 || qtySalad < 0 || qtyWater < 0) {
            throw new InvalidResourceQuantityException();
        }

        return new Resource(new Burger(qtyBurger), new Salad(qtySalad), new Water(qtyWater));
    }
}
