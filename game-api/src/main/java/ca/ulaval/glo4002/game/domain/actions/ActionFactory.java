package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.Resources;

public class ActionFactory {
    public Action create(Dinosaur dinosaur, Herd herd) {
        return new AddDino(dinosaur, herd);
    }

    public Action create(Resources resources, Pantry pantry) {
        return new AddResource(resources, pantry);
    }
}
