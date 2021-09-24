package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import java.util.UUID;

public class AddDino extends Actions{

    public AddDino(UUID id, Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        super(id, dinosaur, dinosaurRepository);
    }

    @Override
    public void execute() {
        ((DinosaurRepository)getRepository()).save((Dinosaur) getObject());
        System.out.println("Add dinosaur to dino repository");
    }
}
