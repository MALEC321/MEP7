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
    private boolean hungry;

    private boolean starving;

    private final DietType diet;
    public Dinosaur(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.strength = calculateStrength();
        this.hungry = true;
        this.starving = false;
        this.diet = SpeciesDietsCorrespondances.getDietFromSpecies(species);
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

    public boolean isStarving() {
        return starving;
    }

    public void setStarving(boolean starving) {
        this.starving = starving;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean isHungry) {
        this.hungry = hungry;
    }

    private int calculateStrength() {
        BigDecimal herbivoreMultiplicand = new BigDecimal("1");
        BigDecimal meatEaterMultiplicand = new BigDecimal("1.5");
        BigDecimal dietMultiplicand = diet == DietType.HERBIVORE ? herbivoreMultiplicand : meatEaterMultiplicand;

        BigDecimal femaleMultiplicand = new BigDecimal("1.5");
        BigDecimal maleMultiplicand = new BigDecimal("1");
        BigDecimal sexMultiplicand = gender.equals("f") ? femaleMultiplicand : maleMultiplicand;

        BigDecimal strength = new BigDecimal(weight).multiply(dietMultiplicand).multiply(sexMultiplicand);
        return strength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getWaterNeeds() {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal waterMultiplicand = new BigDecimal("0.6");

        BigDecimal waterNeeds = weight.multiply(waterMultiplicand);

        if (this.isHungry()) {
            waterNeeds = calculateFoodNeedsForHungryDino(waterNeeds);
        }

        this.hungry = false; // Needed here because we finish with feeding dinosaurs with water

        return waterNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }

    public int getBurgersNeeds() {
        if (this.diet == DietType.HERBIVORE) {
            return 0;
        }

        BigDecimal burgersMultiplicand = new BigDecimal("0.2");
        return calculateFoodNeeds(burgersMultiplicand);
    }

    public int getSaladsNeeds() {
        if (this.diet == DietType.CARNIVORE) {
            return 0;
        }

        BigDecimal saladsMultiplicand = new BigDecimal("0.5");
        return calculateFoodNeeds(saladsMultiplicand);
    }

    private int calculateFoodNeeds(BigDecimal foodMultiplicand) {
        BigDecimal weight = new BigDecimal(this.weight);
        BigDecimal dividend = new BigDecimal(200);

        BigDecimal foodNeeds = weight.multiply(foodMultiplicand).divide(dividend);

        if (this.diet == DietType.OMNIVORE) {
            BigDecimal two = new BigDecimal("2");
            foodNeeds = foodNeeds.divide(two);
        }

        if (this.isHungry()) {
            return calculateFoodNeedsForHungryDino(foodNeeds).setScale(0, RoundingMode.CEILING).intValue();
        }

        return foodNeeds.setScale(0, RoundingMode.CEILING).intValue();
    }

    private BigDecimal calculateFoodNeedsForHungryDino(BigDecimal foodNeeds) {
        BigDecimal doubleResourcesNeeds = new BigDecimal(2);
        return foodNeeds.multiply(doubleResourcesNeeds);
    }
}
