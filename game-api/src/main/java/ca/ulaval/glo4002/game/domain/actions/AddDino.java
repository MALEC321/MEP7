package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;

import java.util.UUID;

public class AddDino extends Action {
    public AddDino(UUID id, Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        super(id, dinosaur, dinosaurRepository);
    }

    @Override
    public void execute() {
        ((DinosaurRepository)getObjectRepository()).save((Dinosaur) getObject());
    }
}
