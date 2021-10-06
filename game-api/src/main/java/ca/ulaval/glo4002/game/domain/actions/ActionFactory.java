package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

import java.util.UUID;

public class ActionFactory {

    public Action create(Dinosaur dinosaur, Command command, DinosaurRepository dinosaurRepository) {
        return (command == Command.ADD_DINO) ? new AddDino(UUID.randomUUID(), dinosaur, dinosaurRepository) :
                new RetrieveAction(UUID.randomUUID(), dinosaur, dinosaurRepository);
    }

    public Action create(ResourceElements resource, Command command, ResourceRepository resourceRepository) {
        return (command == Command.ADD) ? new AddResource(UUID.randomUUID(), resource, resourceRepository) :
                new RetrieveAction(UUID.randomUUID(), resource, resourceRepository);
    }

    public Action create(DinosaurBaby bebe, Command command, DinosaurRepository dinosaurRepository) {
        return (command == Command.ADD_DINO) ? new AddDino(UUID.randomUUID(), bebe, dinosaurRepository) :
                new RetrieveAction(UUID.randomUUID(), bebe, dinosaurRepository);
    }
}
