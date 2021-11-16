package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class FightAction implements Action {
    private Dinosaur challenger;
    private Dinosaur challengee;
    private Herd herd;

    public FightAction(Dinosaur challenger, Dinosaur challengee, Herd herd) {
        this.challenger = challenger;
        this.challengee = challengee;
        this.herd = herd;
    }

    @Override
    public void execute() {
        herd.fight(challenger, challengee);
    }
}
