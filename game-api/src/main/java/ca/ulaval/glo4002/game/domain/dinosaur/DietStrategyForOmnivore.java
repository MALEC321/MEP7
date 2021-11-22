package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietMultiplicand;

public class DietStrategyForOmnivore implements DietStrategy {
    @Override
    public PantryReport calculateFoodNeeds(int weight, boolean isHungry) {
        List<ResourceTypeQuantity> resourceTypeQuantities =
            Arrays.asList(new ResourceTypeQuantity(BURGER, Math.floorDiv(calculateBurgersNeeds(weight, isHungry), 2)),
                new ResourceTypeQuantity(SALAD, Math.floorDiv(calculateSaladsNeeds(weight, isHungry), 2)),
                new ResourceTypeQuantity(WATER, Math.floorDiv(calculateWaterNeeds(weight, isHungry), 2)));
        return new PantryReport(resourceTypeQuantities);
    }

    public int calculateWaterNeeds(int weight, boolean isHungry) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);

        BigDecimal waterNeeds = dinosaurWeight.multiply(DietMultiplicand.WATER.getMultiplicand());

        if (isHungry) {
            waterNeeds = calculateFoodNeedsForHungryDino(waterNeeds);
        }
        return waterNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }

    private BigDecimal calculateFoodNeedsForHungryDino(BigDecimal foodNeeds) {
        BigDecimal doubleResourcesNeeds = new BigDecimal(2);
        return foodNeeds.multiply(doubleResourcesNeeds);
    }

    public int calculateBurgersNeeds(int weight, boolean isHungry) {
        return calculateFoodNeeds(DietMultiplicand.BURGER, weight, isHungry);
    }

    public int calculateSaladsNeeds(int weight, boolean isHungry) {
        return calculateFoodNeeds(DietMultiplicand.SALAD, weight, isHungry);
    }

    public int calculateFoodNeeds(DietMultiplicand dietMultiplicand, int weight, boolean isHungry) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);
        BigDecimal dividend = new BigDecimal(200);
        BigDecimal two = new BigDecimal("2");

        BigDecimal foodNeeds = dinosaurWeight.multiply(dietMultiplicand.getMultiplicand()).divide(dividend);
        foodNeeds = foodNeeds.divide(two);

        if (isHungry) {
            return calculateFoodNeedsForHungryDino(foodNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return foodNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }
}
