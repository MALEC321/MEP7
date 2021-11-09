package ca.ulaval.glo4002.game.application.resources;

import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourceType;
import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

public class ResourcesFactory {
    public Resources create(ResourceType resourceType, int quantity) {
        if (quantity < 0) {
            throw new InvalidResourceQuantityException();
        } else {
            return new Resources(resourceType, quantity, resourceType.getExpiration());
        }
    }
}
