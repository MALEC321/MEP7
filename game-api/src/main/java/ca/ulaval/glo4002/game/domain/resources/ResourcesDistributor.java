package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;

public class ResourcesDistributor {
    public void feedDinosaur(Eatable eatable, Dinosaur dinosaur) {
        if (!eatable.removeResource(SALAD, dinosaur.calculateSaladsNeeds())) {
            dinosaur.setStarving(true);
        }
        if (!eatable.removeResource(BURGER, dinosaur.calculateBurgersNeeds())) {
            dinosaur.setStarving(true);
        }
        if (!eatable.removeResource(WATER, dinosaur.calculateWaterNeeds())) {
            dinosaur.setStarving(true);
        }
    }
}
