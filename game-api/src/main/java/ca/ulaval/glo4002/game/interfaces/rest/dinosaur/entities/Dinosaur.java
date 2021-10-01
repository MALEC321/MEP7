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
    private final double force;
    private final DietType diet;
    private boolean isNewlyAdded;

    public Dinosaur(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.diet = SpeciesDietsCorrespondances.speciesDictionary.get(species);
        this.isNewlyAdded = true;
        this.force = calculateStrength(weight, gender, diet);
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

    public double getForce() {
        return force;
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

    private double calculateStrength(int weight, String gender, DietType diet) {
        BigDecimal bdDietMultiplicand = (diet.equals(DietType.CARNIVORE)) ? new BigDecimal(1.5) : new BigDecimal(1);
        BigDecimal bdSexMultiplicand = (gender.equals("f")) ? new BigDecimal(1.5) : new BigDecimal(1);
        BigDecimal bdStrength = new BigDecimal(weight).multiply(bdDietMultiplicand).multiply(bdSexMultiplicand);

        return bdStrength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getWaterNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdMultiplicand = new BigDecimal(0.6);

        BigDecimal bdWaterNeed = bdWeight.multiply(bdMultiplicand);

        if (this.isNewlyAdded()) {
            this.isNewlyAdded = false;

            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);

            return bdWaterNeed.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdWaterNeed.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getFoodNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdConsiderationByDietType = new BigDecimal(getConsiderationByDietType());
        BigDecimal bdDividend = new BigDecimal(200);

        BigDecimal bdTotalFoodNeed = bdWeight.multiply(bdConsiderationByDietType).divide(bdDividend);

        if (this.isNewlyAdded()) {
            this.isNewlyAdded = false;

            BigDecimal bdDoubleResourcesNeeds = new BigDecimal(2);

            return bdTotalFoodNeed.multiply(bdDoubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdTotalFoodNeed.setScale(0, RoundingMode.CEILING).intValue() ;
    }

    private double getConsiderationByDietType() {
        return this.diet == DietType.HERBIVORE ? 0.5 : 0.2;
    }
}
