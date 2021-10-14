package ca.ulaval.glo4002.game.application.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.resources.Eatable;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class ZooManager {

    public boolean feedThenCheckIfStarving(Eatable eatable, Dinosaur dinosaur) {

        boolean isStarving = (dinosaur.isHerbivore())? !eatable.removeResource(SALAD, dinosaur.getFoodQuantityNeeded()):
                !eatable.removeResource(BURGER, dinosaur.getFoodQuantityNeeded());
        
        if (!eatable.removeResource(WATER, dinosaur.getWaterQuantityNeeded())) {
            isStarving = true;
        }
        return isStarving;
    }
}
