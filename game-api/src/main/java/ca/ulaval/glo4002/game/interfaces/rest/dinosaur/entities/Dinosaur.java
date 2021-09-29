package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dinosaur {
    private final String name;
    private final int weight;
    private final String gender;
    private final String species;
    private final double force;
    private final String diet;
    private boolean isNewlyAdded;

    public Dinosaur(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.diet = SpeciesDiet.valueOf(species).getDietType();
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

    public String getDiet() {
        return diet;
    }

    public boolean isNewlyAdded() {
        return isNewlyAdded;
    }

    public void setNewlyAdded(boolean isNewlyAdded) {
        this.isNewlyAdded = isNewlyAdded;
    }

    private double calculateStrength(int weight, String gender, String diet) {
        double T = (diet.equals("Carnivore")) ? 1.5 : 1;
        double S = (gender.equals("f")) ? 1.5 : 1;

        return weight * T * S;
    }

    public int getWaterNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdMultiplicand = new BigDecimal(0.6);

        BigDecimal bdWaterNeed = bdWeight.multiply(bdMultiplicand);

        return bdWaterNeed.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getFoodNeed() {
        BigDecimal bdWeight = new BigDecimal(this.weight);
        BigDecimal bdConsiderationByDietType = new BigDecimal(getConsiderationByDietType());
        BigDecimal bdDividend = new BigDecimal(200);

        BigDecimal bdTotalFoodNeed = bdWeight.multiply(bdConsiderationByDietType).divide(bdDividend);

        if (this.isNewlyAdded()) {
            BigDecimal bdDoubleFoodNeed = new BigDecimal(2);

            return bdTotalFoodNeed.multiply(bdDoubleFoodNeed).setScale(0, RoundingMode.CEILING).intValue();
        }

        return bdTotalFoodNeed.setScale(0, RoundingMode.CEILING).intValue() ;
    }

    private double getConsiderationByDietType() {
        return SpeciesDiet.valueOf(this.species).getDietType() == "Herbivore" ? 0.5 : 0.2;
    }
}
