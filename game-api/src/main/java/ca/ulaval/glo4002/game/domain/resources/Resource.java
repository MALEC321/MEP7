package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.Map;
import static ca.ulaval.glo4002.game.domain.resources.ResourceTypesEnum.*;

public class Resource {
    private final Map<ResourceTypesEnum, ResourceElements> resources = new HashMap<>(3) {{
            put(Burger, new Burger(0));
            put(Salad, new Salad(0));
            put(Water, new Water(0));
    }};

    public Resource() {
        this(new Burger(0), new Salad(0), new Water(0));
    }

    public Resource(Burger burger, Salad salad, Water water) {
        resources.replace(Burger, burger);
        resources.replace(Salad, salad);
        resources.replace(Water, water);
    }

    public Map<ResourceTypesEnum, ResourceElements> getResources() {
        return resources;
    }

    public void addResource(ResourceTypesEnum type, int quantity){
        resources.get(type).addQuantity(quantity);
    }

    public int getResourceQuantity(ResourceTypesEnum type){
        return resources.get(type).getQuantity();
    }

    public void clear() {
        for (ResourceElements resource: resources.values()) resource.reset();
    }
}
