package ca.ulaval.glo4002.game.application.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.resources.Eatable;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.WATER;

public class ZooManager {

    public void feedDinosaur(Eatable eatable, Dinosaur dinosaur) {
        if (!eatable.removeResourceQty(SALAD, dinosaur.calculateSaladsNeeds())) dinosaur.setStarving(true);
        if (!eatable.removeResourceQty(BURGER, dinosaur.calculateBurgersNeeds())) dinosaur.setStarving(true);
        if (!eatable.removeResourceQty(WATER, dinosaur.calculateWaterNeeds())) dinosaur.setStarving(true);
    }
}
