package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;

public class ResourcesDistributor {
    public void feedDinosaurs(FoodContainer foodContainer, Herd herd) {
        for (Dinosaur dinosaur: herd.findSortedDinosaursByStrengthThenName()) {
            if (!foodContainer.removeResourceQty(SALAD, dinosaur.calculateSaladsNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!foodContainer.removeResourceQty(BURGER, dinosaur.calculateBurgersNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!foodContainer.removeResourceQty(WATER, dinosaur.calculateWaterNeeds())) {
                dinosaur.setStarving(true);
            }
            if (dinosaur.isStarving()) {
                dinosaur.setDead(true);
                herd.removeDinosaur(dinosaur);
            }
        }
    }
}
