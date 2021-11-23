package ca.ulaval.glo4002.game.application.resources;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import ca.ulaval.glo4002.game.application.exceptions.InvalidResourceQuantityException;
import ca.ulaval.glo4002.game.domain.resources.ResourceType;

class ResourcesFactoryTest {

    private final ResourcesFactory resourcesFactory = new ResourcesFactory();
    private final ResourceType resourceType = ResourceType.BURGER;
    private final int invalidQuantity = -432;

    @Test
    void givenNegativeResource_whenCreateResource_thenInvalidResourceQuantityExceptionIsThrown() {
        assertThrows(InvalidResourceQuantityException.class, () -> resourcesFactory.create(resourceType, invalidQuantity));
    }
}