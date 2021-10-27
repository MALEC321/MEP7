package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;

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
        this.diet = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        this.isNewlyAdded = true;
        this.strength = calculateStrength();
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

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

    public boolean isHerbivore() {
        return diet.equals(DietType.HERBIVORE);
    }

    public boolean isCarnivore() {
        return diet.equals(DietType.CARNIVORE);
    }

    public boolean isOmnivore() {
        return diet.equals(DietType.OMNIVORE);
    }

    private int calculateStrength() {
        BigDecimal bdDietMultiplicand = null;
        if (diet != null) {
            bdDietMultiplicand = (diet.equals(DietType.CARNIVORE) || diet.equals(DietType.OMNIVORE)) ? new BigDecimal("1.5") : new BigDecimal("1");
        }

        BigDecimal bdSexMultiplicand = null;
        if (gender != null) {
            bdSexMultiplicand = (gender.equals("f")) ? new BigDecimal("1.5") : new BigDecimal("1");
        }

        BigDecimal bdStrength = new BigDecimal(weight).multiply(bdDietMultiplicand).multiply(bdSexMultiplicand);
        return bdStrength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getWaterQuantityNeeded() {
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

    public int getFoodQuantityNeeded() {
        return getFoodNeed();
    }

    public int getFoodNeed() {
        BigDecimal bdTotalFoodNeed;
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdConsiderationByDietType = new BigDecimal(getConsiderationByDietType());
        BigDecimal bdDividend = new BigDecimal(200);

        bdTotalFoodNeed = bdWeight.multiply(bdConsiderationByDietType).divide(bdDividend);

        if (diet.equals(DietType.OMNIVORE)){
            BigDecimal burger = bdWeight.multiply(new BigDecimal("0.2")).divide(bdDividend).divide(new BigDecimal(2));
            BigDecimal salad = bdWeight.multiply(new BigDecimal("0.5")).divide(bdDividend).divide(new BigDecimal(2));
            bdTotalFoodNeed = burger.add(salad);
        }

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);
            return bdTotalFoodNeed.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdTotalFoodNeed.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getOmnivoreSaladsNeeds() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdDividend = new BigDecimal(200);

        BigDecimal salads = bdWeight.multiply(new BigDecimal("0.5")).divide(bdDividend).divide(new BigDecimal(2));

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);
            return salads.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return salads.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getOmnivoreBurgersNeeds() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdDividend = new BigDecimal(200);

        BigDecimal burgers = bdWeight.multiply(new BigDecimal("0.2")).divide(bdDividend).divide(new BigDecimal(2));

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);
            return burgers.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return burgers.setScale(0, RoundingMode.CEILING).intValue();
    }

    private String getConsiderationByDietType() {
        switch (this.diet){
            case OMNIVORE:
            case HERBIVORE:
                return "0.5";
            default:
                return "0.2";
        }
    }
}
