package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.resources.ResourceType;

public class ResourceTypeQuantity {
    private final ResourceType resourceType;
    private int quantity;

    public ResourceTypeQuantity(ResourceType resourceType, int quantity) {
        this.resourceType = resourceType;
        this.quantity = quantity;
    }

    public ResourceTypeQuantity(ResourceType resourceType) {
        this.resourceType = resourceType;
        this.quantity = 0;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getQuantity() {
        return quantity;
    }
    public void add(int quantity) {
        this.quantity += quantity;
    }
}
