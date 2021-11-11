package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;

import java.util.HashMap;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourcesGroup {
    private final ResourcesFactory resourcesFactory = new ResourcesFactory();
    private final Map<ResourceType, Resources> resources;

    public ResourcesGroup() {
        resources = new HashMap<>(3) {{
                put(BURGER, resourcesFactory.create(BURGER, 0));
                put(SALAD, resourcesFactory.create(SALAD, 0));
                put(WATER, resourcesFactory.create(WATER, 0));
            }};
    }

    public ResourcesGroup(Resources burger, Resources salad, Resources water) {
        this();
        resources.replace(BURGER, burger);
        resources.replace(SALAD, salad);
        resources.replace(WATER, water);
    }

    public Map<ResourceType, Resources> getResources() {
        return resources;
    }

    public void addResource(ResourceType type, int quantity) {
        resources.get(type).addQuantity(quantity);
    }

    public int getResourceQuantity(ResourceType type) {
        return resources.get(type).getQuantity();
    }

    public void clear() {
        for (Resources resources : this.resources.values()) {
            resources.reset();
        }
    }
}
