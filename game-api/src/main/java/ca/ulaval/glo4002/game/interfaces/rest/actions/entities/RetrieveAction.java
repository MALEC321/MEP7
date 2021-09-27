package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceElements;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;

import java.util.UUID;

public class RetrieveAction extends Action {
    public RetrieveAction(UUID id, ResourceElements resource, ResourceRepository resourceRepository) {
        super(id, resource, resourceRepository);
    }

    public RetrieveAction(UUID id, Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        super(id, dinosaur, dinosaurRepository);
    }

    @Override
    public void execute() {
        System.out.println("Get resource in repository and retrieve a value of it");
    }
}
