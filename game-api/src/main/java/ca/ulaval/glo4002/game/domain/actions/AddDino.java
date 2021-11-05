package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

public class AddDino extends Action {
    public AddDino(UUID id, Dinosaur dinosaur, HerdRepository herdRepository) {
        super(id, dinosaur, herdRepository);
    }

    @Override
    public void execute() {
        ((HerdRepository) getObjectRepository()).findHerd().add((Dinosaur) getObject());
    }
}
