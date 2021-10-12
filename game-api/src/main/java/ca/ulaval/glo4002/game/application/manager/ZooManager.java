package ca.ulaval.glo4002.game.application.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.resources.Eatable;
import ca.ulaval.glo4002.game.domain.resources.ResourceType;

public class ZooManager {

    public boolean feedThenCheckIfStarving(Eatable eatable, Dinosaur dinosaur) {

        boolean isStarving = (dinosaur.isHerbivore())? !eatable.remove(dinosaur.getFoodQuantityNeeded(), ResourceType.SALAD):
                !eatable.remove(dinosaur.getFoodQuantityNeeded(), ResourceType.BURGER);
        
        if (!eatable.remove(dinosaur.getWaterQuantityNeeded(), ResourceType.WATER)) {
            isStarving = true;
        }
        return isStarving;
    }
}
