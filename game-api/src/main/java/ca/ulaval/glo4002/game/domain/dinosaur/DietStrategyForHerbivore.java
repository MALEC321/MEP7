package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietMultiplicand;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static ca.ulaval.glo4002.game.domain.resources.ResourceType.*;

public class DietStrategyForHerbivore implements DietStrategy {
    @Override
    public OrderForm calculateFoodNeeds(int weight, boolean isHungry) {
        OrderForm orderForm = new OrderForm();
        orderForm.addFoods(new ResourceTypeQuantity(BURGER, 0),
            new ResourceTypeQuantity(SALAD, calculateSaladNeeds(weight, isHungry)),
            new ResourceTypeQuantity(WATER, calculateWaterNeeds(weight, isHungry)));
        return orderForm;
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

    public int calculateSaladNeeds(int weight, boolean isHungry) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);
        BigDecimal dividend = new BigDecimal("200");

        BigDecimal foodNeeds = dinosaurWeight.multiply(DietMultiplicand.SALAD.getMultiplicand());
        foodNeeds = foodNeeds.divide(dividend);

        if (isHungry) {
            return calculateFoodNeedsForHungryDino(foodNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return foodNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }
}
