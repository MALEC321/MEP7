package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

public class ActionFactory {
    public Action create(Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        return new AddDino(UUID.randomUUID(), dinosaur, dinosaurRepository);
    }

    public Action create(Resources resources, ResourceRepository resourceRepository) {
        return new AddResource(UUID.randomUUID(), resources, resourceRepository);
    }
}
