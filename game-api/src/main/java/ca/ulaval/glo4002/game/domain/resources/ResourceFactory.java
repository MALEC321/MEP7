package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.exceptions.types.InvalidResourceQuantityException;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ResourceFactory {
    public Resource create(ResourceType resourceType, int quantity) throws Exception {
        if (quantity < 0) {
            throw new InvalidResourceQuantityException();
        }
        else if (resourceType.equals(BURGER))
            return new Resource(BURGER, quantity, 4);
        else if (resourceType.equals(SALAD))
            return new Resource(SALAD, quantity, 3);
        else if (resourceType.equals(WATER))
            return new Resource(SALAD, quantity, 10);
        else
            throw new Exception(); //Remplacer par InvalidResourceTypeException()
    }
}
