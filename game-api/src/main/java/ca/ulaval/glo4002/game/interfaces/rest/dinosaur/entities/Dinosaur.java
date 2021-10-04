package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDietsCorrespondances;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dinosaur {
    private final String name;
    private final int weight;
    private final String gender;
    private final String species;
    private final int strength;
    private final DietType diet;
    private boolean isNewlyAdded;

    public Dinosaur(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.diet = SpeciesDietsCorrespondances.speciesDictionary.get(species);
        this.isNewlyAdded = true;
        this.strength = calculateStrength(weight, gender, diet);
    }

    public String getName() {
        return name;
    }

    public int getWeight() { return weight; }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }

    public int getStrength() {
        return strength;
    }

    public DietType getDiet() {
        return diet;
    }

    public boolean isNewlyAdded() {
        return isNewlyAdded;
    }

    public void setNewlyAdded(boolean isNewlyAdded) {
        this.isNewlyAdded = isNewlyAdded;
    }

    private int calculateStrength(int weight, String gender, DietType diet) {
        BigDecimal bdDietMultiplicand = (diet.equals(DietType.CARNIVORE)) ? new BigDecimal("1.5") : new BigDecimal("1");
        BigDecimal bdSexMultiplicand = (gender.equals("f")) ? new BigDecimal("1.5") : new BigDecimal("1");
        BigDecimal bdStrength = new BigDecimal(weight).multiply(bdDietMultiplicand).multiply(bdSexMultiplicand);

        return bdStrength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int feedWater() {
        int waterNeed = getWaterNeed();
        this.isNewlyAdded = false;

        return waterNeed;
    }

    public int getWaterNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdMultiplicand = new BigDecimal("0.6");

        BigDecimal bdWaterNeed = bdWeight.multiply(bdMultiplicand);

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);

            return bdWaterNeed.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdWaterNeed.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int feedFood() {
        return getFoodNeed();
    }

    public int getFoodNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdConsiderationByDietType = new BigDecimal(getConsiderationByDietType());
        BigDecimal bdDividend = new BigDecimal(200);

        BigDecimal bdTotalFoodNeed = bdWeight.multiply(bdConsiderationByDietType).divide(bdDividend);

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);

            return bdTotalFoodNeed.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdTotalFoodNeed.setScale(0, RoundingMode.CEILING).intValue() ;
    }

    private String getConsiderationByDietType() {
        return this.diet == DietType.HERBIVORE ? "0.5" : "0.2";
    }
}
