package ca.ulaval.glo4002.game.domain.dinosaur;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;

public class Dinosaur {
    private String name;
    private int weight;
    private String gender;
    private int strength;
    private boolean hungry;
    private DietStrategy dietStrategy;
    private DietType diet;
    private String species;
    private Dinosaur mother;
    private Dinosaur father;
    private boolean isAdult;
    private boolean isDead;
    private boolean secondTimeEating = false;
    private boolean fighting;
    public Dinosaur(String name, int weight, String gender, DietStrategy dietStrategy, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.strength = calculateStrength();
        this.hungry = true;
        this.diet = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        this.species = species;
        this.dietStrategy = dietStrategy;
        this.isAdult = true;
        this.isDead = false;
    }

    public Dinosaur(String name, int weight, String gender, DietStrategy dietStrategy, Dinosaur mother, Dinosaur father, String species) {
        this.mother = mother;
        this.father = father;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.strength = calculateStrength();
        this.hungry = true;
        this.dietStrategy = dietStrategy;
        this.species = species;
        this.diet = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        this.isAdult = false;
        this.isDead = false;
    }

    public Dinosaur() {
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

    public int getStrength() {
        return strength;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean isHungry) {
        this.hungry = isHungry;
    }

    public DietType getDiet() {
        return diet;
    }

    public ResourcesStateDto eat(ResourcesStateDto resourcesStateDto) {
        ResourcesStateDto resourceStateLeft;
        ResourcesStateDto resourceStateNeeded = dietStrategy.calculateFoodNeeds(weight, isHungry());
        if (!resourcesStateDto.checkIfThereIsEnoughQuantity(resourceStateNeeded)) {
            setDead(true);
        }
        resourceStateLeft = resourcesStateDto.removeQuantities(resourceStateNeeded);
        if (diet != DietType.OMNIVORE) {
            setHungry(false);
        }
        if (diet == DietType.OMNIVORE && secondTimeEating) {
            setHungry(false);
        }
        secondTimeEating = true;

        return resourceStateLeft;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean areBothParentsDead() {
        return !this.isAdult && this.mother.isDead && this.father.isDead;
    }

    public String getSpecies() {
        return species;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Dinosaur dinosaur = (Dinosaur) o;
        return getName().equals(dinosaur.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public boolean isFighting() {
        return fighting;
    }

    public void setFighting(boolean fighting) {
        this.fighting = fighting;
    }

    public void fight() {
        this.fighting = true;
    }
}
