package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;

public class AddDino implements Action {

    private Dinosaur dinosaur;
    private DinosaurRepository dinosaurRepository;

    public AddDino(Dinosaur dinosaur, DinosaurRepository dinosaurRepository) {
        this.dinosaur = dinosaur;
        this.dinosaurRepository = dinosaurRepository;
    }

    @Override
    public void execute() {
        dinosaurRepository.save(dinosaur);
    }
}
