package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.exceptions.ArmsTooShortException;
import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;
import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;

import java.util.List;

public class ActionFactory {
    public Action create(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public Action create(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }

    public Action createFight(List<Action> actions, Dinosaur challenger, Dinosaur challengee, Herd herd) {
        int numberOfCurrentFights = 0;
        for (Action action : actions) {
            if (action instanceof FightAction) {
                numberOfCurrentFights++;
            }
        }
        if (numberOfCurrentFights >= 2) {
            throw new MaxCombatsReachedException();
        } else if (challenger == null || challengee == null) {
            throw new NotExistentNameException();
        } else if (challenger.getSpecies().equals("Tyrannosaurus Rex") || challengee.getSpecies().equals("Tyrannosaurus Rex")) { //Un enum aurait-il été mieux?
            throw new ArmsTooShortException();
        } else if (challenger.isFighting() || challengee.isFighting()) {
            throw new DinosaurAlreadyParticipatingException();
        } else {
            challenger.fight();
            challengee.fight();

            return new FightAction(challenger, challengee, herd);
        }
    }
}
