package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

public class RemoveOrphanedBabyDinosaursAction implements Action {

    private Herd herd;

    public RemoveOrphanedBabyDinosaursAction(Herd herd) {
        this.herd = herd;
    }

    public Herd getHerd() {
        return herd;
    }

    @Override
    public void execute() {
        herd.removeOrphanedBabyDinosaurs();
    }
}
