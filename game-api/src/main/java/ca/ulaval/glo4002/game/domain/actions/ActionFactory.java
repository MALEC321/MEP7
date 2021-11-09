package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

public class ActionFactory {
    public Action create(Dinosaur dinosaur, HerdRepository herdRepository) {
        return new AddDino(UUID.randomUUID(), dinosaur, herdRepository);
    }

    public Action create(Resources resources, ResourceRepository resourceRepository) {
        return new AddResource(UUID.randomUUID(), resources, resourceRepository);
    }
}
