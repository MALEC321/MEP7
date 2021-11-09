package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;

public class ResourcesDistributor {
    public void feedDinosaurs(FoodContainer foodContainer, Herd herd) {
        for (Dinosaur dinosaur: herd.findSortedDinosaursByStrengthThenName()) {
            if (!foodContainer.removeResource(SALAD, dinosaur.calculateSaladsNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!foodContainer.removeResource(BURGER, dinosaur.calculateBurgersNeeds())) {
                dinosaur.setStarving(true);
            }
            if (!foodContainer.removeResource(WATER, dinosaur.calculateWaterNeeds())) {
                dinosaur.setStarving(true);
            }

            if (dinosaur.isStarving()) {
                herd.removeDinosaur(dinosaur);
            }
        }
    }
}
