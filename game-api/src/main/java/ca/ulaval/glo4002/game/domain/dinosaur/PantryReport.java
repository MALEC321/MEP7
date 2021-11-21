package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.resources.ResourceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PantryReport {
    private final Map<ResourceType, Integer> pantryQuantities = new HashMap<>();

    public PantryReport(List<ResourceTypeQuantity> resourceTypeQuantities) {
        for (ResourceTypeQuantity resourceTypeQuantity: resourceTypeQuantities) {
            pantryQuantities.put(resourceTypeQuantity.getResourceType(), resourceTypeQuantity.getQuantity());
        }
    }

    public int getQtyForResourceType(ResourceType resourceType) {
        return pantryQuantities.get(resourceType);
    }

    public Map<ResourceType, Integer> getPantryQuantities() {
        return pantryQuantities;
    }
}
