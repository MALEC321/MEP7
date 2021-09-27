package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceType;
import java.util.UUID;

public class ActionFactory {

    public Actions create(Object object, Command command, Object repository) {
        return (command == Command.ADD)? new AddAction(UUID.randomUUID(), (ResourceType) object, command) :
                new AddDino(UUID.randomUUID(), (Dinosaur) object, (DinosaurRepository) repository);
    }
}
