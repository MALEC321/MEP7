package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.OMNIVORE;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietMultiplicand;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

public class WaterNeedsCalculator {
    public int calculateNeeds(int weight, boolean isHungry, DietType dietType) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);

        BigDecimal waterNeeds = dinosaurWeight.multiply(DietMultiplicand.WATER.getMultiplicand());

        if (dietType == OMNIVORE) {
            waterNeeds = waterNeeds.multiply(new BigDecimal("0.5"));
        }
        if (isHungry) {
            waterNeeds = waterNeeds.multiply(new BigDecimal("2"));
        }
        return waterNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }
}
