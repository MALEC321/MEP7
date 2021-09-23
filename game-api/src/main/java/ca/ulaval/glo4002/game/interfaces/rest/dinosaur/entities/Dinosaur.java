package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.DietType;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;

public class Dinosaur {
    public String name;
    public int weight;
    public String gender;
    public String species;
    private final double force;
    private final String diet;
    private boolean isNewlyAdded;

    public Dinosaur(String name, int weight, String gender, String species, String diet) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.diet = diet;
        this.isNewlyAdded = true;
        this.force = calculateStrength(weight, gender, diet);
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

    public double getWaterNeed() {
        return Math.round(this.weight * 0.6) + 2 * 3;
    }

    public double getFoodNeed() {
        double foodNeed =
                (this.getWeight() * getConsiderationByDietType()) / 200;

        if (this.isNewlyAdded) {
            foodNeed = Math.ceil(foodNeed * 2);
        }

        return foodNeed;
    }

    private double getConsiderationByDietType() {
        return SpeciesDiet.valueOf(this.species).name().equals(DietType.HERBIVORE.name()) ? 0.5 : 0.2;
    }
}
