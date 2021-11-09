package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

public class AddDino implements Action {

    private Dinosaur dinosaur;
    private HerdRepository herdRepository;

    public AddDino(Dinosaur dinosaur, HerdRepository herdRepository) {
        this.dinosaur = dinosaur;
        this.herdRepository = herdRepository;
    }

    @Override
    public void execute() {
        herdRepository.findHerd().addDinosaur(dinosaur);
    }
}
