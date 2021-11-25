package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietMultiplicand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WaterNeedsCalculatorTest {
    public static final BigDecimal MULTIPLICAND_HUNGRY_STATE = new BigDecimal("2");
    private static final int WEIGHT = 100;
    private static final boolean IS_NOT_HUNGRY = false;
    private static final boolean IS_HUNGRY = true;
    private WaterNeedsCalculator waterNeedsCalculator;

    @BeforeEach
    void setUp() {
        waterNeedsCalculator = new WaterNeedsCalculator();
    }

    @Test
    void omnivoreDietType_whenCalculateWaterNeeds_thenReturnedCorrectWaterNeedsForOmnivore() {
        int expectedWaterNeeds = createExpectedWaterNeedsForOmnivoreDietType(WEIGHT);

        assertTrue(expectedWaterNeeds == waterNeedsCalculator.calculateNeeds(WEIGHT, IS_NOT_HUNGRY, OMNIVORE));
    }

    @Test
    void notOmnivoreDietType_whenCalculateWaterNeeds_thenReturnedCorrectWaterNeedsForNoneOmnivoreDietType() {
        int expectedWaterNeeds = createAllDietWaterNeedsExpected(WEIGHT).intValue();

        assertTrue(expectedWaterNeeds == waterNeedsCalculator.calculateNeeds(WEIGHT, IS_NOT_HUNGRY, HERBIVORE));
        assertTrue(expectedWaterNeeds == waterNeedsCalculator.calculateNeeds(WEIGHT, IS_NOT_HUNGRY, CARNIVORE));
    }

    @Test
    void notOmnivoreDietType_whenCalculateWaterNeedsOnHungryState_thenReturnedCorrectWaterNeedsForNoneOmnivoreDietType() {
        int expectedWaterNeeds = createExpectedWaterNeedsForHungryState(WEIGHT);

        assertTrue(expectedWaterNeeds == waterNeedsCalculator.calculateNeeds(WEIGHT, IS_HUNGRY, HERBIVORE));
        assertTrue(expectedWaterNeeds == waterNeedsCalculator.calculateNeeds(WEIGHT, IS_HUNGRY, CARNIVORE));
    }

    private BigDecimal createAllDietWaterNeedsExpected(int weight) {
        BigDecimal dinosaurWeight = new BigDecimal(weight);
        BigDecimal waterNeeds = dinosaurWeight.multiply(DietMultiplicand.WATER.getMultiplicand());
        return waterNeeds;
    }

    private int createExpectedWaterNeedsForOmnivoreDietType(int weight) {
        BigDecimal expected = createAllDietWaterNeedsExpected(weight);
        return expected.multiply(new BigDecimal("0.5")).setScale(0, RoundingMode.CEILING).intValue();
    }

    private int createExpectedWaterNeedsForHungryState(int weight) {
        BigDecimal dinosaurWeight = createAllDietWaterNeedsExpected(weight);
        BigDecimal dinosaurWeightBy2 = dinosaurWeight.multiply(MULTIPLICAND_HUNGRY_STATE);
        return dinosaurWeightBy2.setScale(0, RoundingMode.CEILING).intValue();
    }
}