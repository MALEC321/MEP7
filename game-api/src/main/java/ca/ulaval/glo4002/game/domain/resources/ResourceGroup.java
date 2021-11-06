package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourceFactory;

import java.util.HashMap;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceGroup {
    private ResourceFactory resourceFactory = new ResourceFactory();
    private final Map<ResourceType, Resource> resources = new HashMap<>(3) {{
            put(BURGER, resourceFactory.create(BURGER, 0));
            put(SALAD, resourceFactory.create(SALAD, 0));
            put(WATER, resourceFactory.create(WATER, 0));
        }};

    public ResourceGroup() {
        this(0, 0, 0);
    }

    public ResourceGroup(int qtyBurger, int qtySalad, int qtyWater) {
        resources.replace(BURGER, resourceFactory.create(BURGER, qtyBurger));
        resources.replace(SALAD, resourceFactory.create(SALAD, qtySalad));
        resources.replace(WATER, resourceFactory.create(WATER, qtyWater));

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
