package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.OMNIVORE;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietMultiplicand;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

public class DietStrategyForHerbivore extends AbstractDietStrategy {
    private final WaterNeedsCalculator waterNeedsCalculator;

    public DietStrategyForHerbivore(DietType dietType) {
        super(dietType);
        waterNeedsCalculator = new WaterNeedsCalculator();
    }

    @Override
    public ResourcesStateDto calculateFoodNeeds(int weight, boolean isHungry) {
        List<ResourceTypeQuantity> resourceTypeQuantities = Arrays.asList(new ResourceTypeQuantity(BURGER, 0),
            new ResourceTypeQuantity(SALAD, calculateSaladNeeds(weight, isHungry)),
            new ResourceTypeQuantity(WATER, waterNeedsCalculator.calculateNeeds(weight, isHungry, getDietType())));
        return new ResourcesStateDto(resourceTypeQuantities);
    }

    private BigDecimal calculateFoodNeedsForHungryDino(BigDecimal foodNeeds) {
        BigDecimal doubleResourcesNeeds = new BigDecimal(2);
        return foodNeeds.multiply(doubleResourcesNeeds);
    }

    public int calculateSaladNeeds(int weight, boolean isHungry) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);
        BigDecimal dividend = new BigDecimal("200");

        BigDecimal foodNeeds = dinosaurWeight.multiply(DietMultiplicand.SALAD.getMultiplicand());
        foodNeeds = foodNeeds.divide(dividend);

        if (getDietType() == OMNIVORE) {
            foodNeeds = foodNeeds.multiply(new BigDecimal("0.5"));
        }
        foodNeeds = foodNeeds.setScale(0, RoundingMode.CEILING);

        if (isHungry) {
            foodNeeds = calculateFoodNeedsForHungryDino(foodNeeds);
        }

        return foodNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }
}
