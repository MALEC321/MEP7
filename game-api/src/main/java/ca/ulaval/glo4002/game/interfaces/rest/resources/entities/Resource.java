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
}
