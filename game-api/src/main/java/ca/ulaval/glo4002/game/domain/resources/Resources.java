package ca.ulaval.glo4002.game.domain.resources;

import java.util.HashMap;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class Resources {
    private final Map<ResourceType, Resource> resources = new HashMap<>(3) {{
            put(BURGER, new Burger(0));
            put(SALAD, new Salad(0));
            put(WATER, new Water(0));
        }};

    public Resources() {
        this(new Burger(0), new Salad(0), new Water(0));
    }

    public Resources(Burger burger, Salad salad, Water water) {
        resources.replace(BURGER, burger);
        resources.replace(SALAD, salad);
        resources.replace(WATER, water);
    }

    public Map<ResourceType, Resource> getResources() {
        return resources;
    }

    public void addResource(ResourceType type, int quantity) {
        resources.get(type).addQuantity(quantity);
    }

    public int getResourceQuantity(ResourceType type) {
        return resources.get(type).getQuantity();
    }

    public void clear() {
        for (Resource resource : resources.values()) {
            resource.reset();
        }
    }
}
