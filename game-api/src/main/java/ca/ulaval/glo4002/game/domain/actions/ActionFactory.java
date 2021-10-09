package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.UUID;

public class ActionFactory {

    public Action create(Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        return new AddDino(UUID.randomUUID(), dinosaur, dinosaurRepository);
    }

    public Action create(ResourceElements resource, ResourceRepository resourceRepository) {
        return new AddResource(UUID.randomUUID(), resource, resourceRepository);
    }
}
