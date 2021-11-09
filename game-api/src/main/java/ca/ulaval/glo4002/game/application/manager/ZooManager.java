package ca.ulaval.glo4002.game.application.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.resources.FoodContainer;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;

public class ZooManager {
    public void feedDinosaurs(FoodContainer eatable, Herd herd) {
        for (Dinosaur dinosaur: herd.findSortedDinosaursByStrengthThenName()) {
            if (!eatable.removeResource(SALAD, dinosaur.calculateSaladsNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!eatable.removeResource(BURGER, dinosaur.calculateBurgersNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!eatable.removeResource(WATER, dinosaur.calculateWaterNeeds())) {
                dinosaur.setStarving(true);
            }
            if (dinosaur.isStarving()) {
                herd.removeDinosaur(dinosaur);
            }
        }
    }
}
