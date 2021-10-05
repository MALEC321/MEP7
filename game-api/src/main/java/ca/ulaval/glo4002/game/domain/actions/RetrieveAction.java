package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;

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
