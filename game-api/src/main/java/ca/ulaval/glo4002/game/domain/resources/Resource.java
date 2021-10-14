package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Resource {
    private final Map<ResourceType, ResourceElements> resources = new HashMap<>(3) {{
            put(BURGER, new Burger(0));
            put(SALAD, new Salad(0));
            put(WATER, new Water(0));
        }};

    public Resource() {
        this(new Burger(0), new Salad(0), new Water(0));
    }

    public Resource(Burger burger, Salad salad, Water water) {
        resources.replace(BURGER, burger);
        resources.replace(SALAD, salad);
        resources.replace(WATER, water);
    }

    public Map<ResourceType, ResourceElements> getResources() {
        return resources;
    }

    public void addResource(ResourceType type, int quantity) {
        resources.get(type).addQuantity(quantity);
    }

    public int getResourceQuantity(ResourceType type) {
        return resources.get(type).getQuantity();
    }

    public void clear() {
        for (ResourceElements resource : resources.values()) {
            resource.reset();
        }
    }
}
