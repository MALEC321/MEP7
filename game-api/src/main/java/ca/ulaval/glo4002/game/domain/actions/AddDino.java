package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class AddDino implements Action {
    private Dinosaur dinosaur;
    private Herd herd;

    public AddDino(Dinosaur dinosaur, Herd herd) {
        this.dinosaur = dinosaur;
        this.herd = herd;
    }

    public Dinosaur getDinosaur() {
        return dinosaur;
    }

    public Herd getHerd() {
        return herd;
    }

    @Override
    public void execute() {
        herd.addDinosaur(dinosaur);
    }
}
