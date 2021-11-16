package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
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

    //Est-ce à la bonne place?
    public Action fight(List<Action> actions, Dinosaur challenger, Dinosaur challengee, Herd herd) {
        int fightAmount = 0;
        for (Action action : actions)
            if (action instanceof FightAction)
                fightAmount++;
        if (fightAmount == 2)
            throw new MaxCombatsReachedException();
        else
            return new FightAction(challenger, challengee, herd);
    }
}
