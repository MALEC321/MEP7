package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.HashMap;
import java.util.Map;

public class Resource {
    private final Map<String, ResourceElements> resources = new HashMap<String, ResourceElements>(3){{
            put("Burgers", new Burger(0));
            put("Salads", new Salad(0));
            put("Water", new Water(0));
    }};

    public Resource(Burger burger, Salad salad, Water water) {
        resources.replace("Burgers", burger);
        resources.replace("Salads", salad);
        resources.replace("Water", water);
    }

    public Map<String, ResourceElements> getResources() {
        return resources;
    }

    public void addBurger(int quantity) {
        resources.get("Burgers").addQuantity(quantity);
    }

    public void addSalad(int quantity) {
        resources.get("Salads").addQuantity(quantity);
    }

    public void addWater(int quantity) {
        resources.get("Water").addQuantity(quantity);
    }

    public int getBurgersQuantity() {
        return resources.get("Burgers").getQuantity();
    }
    public int getSaladQuantity() {
        return resources.get("Salads").getQuantity();
    }
    public int getWaterQuantity() {
        return resources.get("Water").getQuantity();
    }

    public void clear() {
        for (ResourceElements resource: resources.values()) resource.reset();
    }
}
