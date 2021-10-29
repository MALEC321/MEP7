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
        BigDecimal dietMultiplicand = (diet.equals(DietType.CARNIVORE) || diet.equals(DietType.OMNIVORE)) ? new BigDecimal("1.5") : new BigDecimal("1");
        BigDecimal sexMultiplicand = (gender.equals("f")) ? new BigDecimal("1.5") : new BigDecimal("1");

        BigDecimal strength = new BigDecimal(weight).multiply(dietMultiplicand).multiply(sexMultiplicand);
        return strength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getWaterNeeds() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal multiplicand = new BigDecimal("0.6");

        int waterNeeds = calculateNutritionNeeds(weight.multiply(multiplicand));

        this.isNewlyAdded = false; // Needed here because we finish with feeding dinosaurs water

        return waterNeeds;
    }

    public int getFoodNeeds() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal multiplicand = new BigDecimal(getConsiderationByDietType());
        BigDecimal dividend = new BigDecimal(200);

        int foodNeeds = calculateNutritionNeeds(weight.multiply(multiplicand).divide(dividend));

        return foodNeeds;
    }

    public int getOmnivoreSaladsNeeds() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal multiplicand = new BigDecimal("0.5");
        BigDecimal dividend = new BigDecimal(200);

        BigDecimal salads = weight.multiply(multiplicand).divide(dividend).divide(new BigDecimal(2));

        return calculateNutritionNeeds(salads);
    }

    public int getOmnivoreBurgersNeeds() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal multiplicand = new BigDecimal("0.2");
        BigDecimal dividend = new BigDecimal(200);

        BigDecimal burgers = weight.multiply(multiplicand).divide(dividend).divide(new BigDecimal(2));

        return calculateNutritionNeeds(burgers);
    }

    private int calculateNutritionNeeds(BigDecimal nutritionNeeds) {
        if (this.isNewlyAdded()) {
            BigDecimal doubleResourcesNeeds = new BigDecimal(2);
            return nutritionNeeds.multiply(doubleResourcesNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return nutritionNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }

    private String getConsiderationByDietType() {
        switch (this.diet){
            case OMNIVORE:
            case HERBIVORE:
                return "0.5";
            case CARNIVORE:
                return "0.2";
            default:
                throw new RuntimeException("unreachable getConsiderationByDietType parameter");
        }
    }
}
