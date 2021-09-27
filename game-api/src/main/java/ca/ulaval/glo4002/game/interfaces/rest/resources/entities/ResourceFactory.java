package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class ResourceFactory {
    public Resource create(int qtyBurger, int qtySalad, int qtyWater) {
        return new Resource(new Burger(qtyBurger), new Salad(qtySalad), new Water(qtyWater));
    }
}
