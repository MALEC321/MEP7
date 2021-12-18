package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Dinosaur {
    private String name;
    private int weight;
    private String gender;
    private int strength;
    private boolean isHungry;
    private DietStrategy dietStrategy;
    private DietType dietType;
    private String species;
    private Dinosaur mother;
    private Dinosaur father;
    private boolean isAdult;
    private boolean isDead;
    private boolean secondTimeEating;
    private boolean isFighting;

    public Dinosaur(String name, int weight, String gender, DietStrategy dietStrategy, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.isHungry = true;
        this.species = species;
        this.dietStrategy = dietStrategy;
        this.isAdult = true;
        this.isDead = false;
        this.secondTimeEating = false;
        this.dietType = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        this.strength = calculateStrength();
    }

    public Dinosaur(String name, int weight, String gender, DietStrategy dietStrategy, Dinosaur mother, Dinosaur father, String species) {
        this.mother = mother;
        this.father = father;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.isHungry = true;
        this.dietStrategy = dietStrategy;
        this.species = species;
        this.isAdult = false;
        this.isDead = false;
        this.dietType = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        this.strength = calculateStrength();
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

    public boolean getIsHungry() {
        return isHungry;
    }

    public void setIsHungry(boolean isHungry) {
        this.isHungry = isHungry;
    }

    public DietType getDietType() {
        return dietType;
    }

    public ResourcesStateDto eat(ResourcesStateDto resourcesStateDto) {
        ResourcesStateDto resourceStateLeft;
        ResourcesStateDto resourceStateNeeded = dietStrategy.calculateFoodNeeds(weight, getIsHungry());
        if (!resourcesStateDto.checkIfThereIsEnoughQuantity(resourceStateNeeded)) {
            setIsDead(true);
        }
        resourceStateLeft = resourcesStateDto.removeQuantities(resourceStateNeeded);
        if (dietType != DietType.OMNIVORE) {
            setIsHungry(false);
        }
        if (dietType == DietType.OMNIVORE && secondTimeEating) {
            setIsHungry(false);
        }
        secondTimeEating = true;

        return resourceStateLeft;
    }

    public boolean getIsDead() {
        return isDead;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean isOrphan() {
        return !this.isAdult && this.mother.isDead && this.father.isDead;
    }

    public String getSpecies() {
        return species;
    }

    private int calculateStrength() {
        BigDecimal herbivoreMultiplicand = new BigDecimal("1");
        BigDecimal meatEaterMultiplicand = new BigDecimal("1.5");
        BigDecimal dietMultiplicand = dietType == DietType.HERBIVORE ? herbivoreMultiplicand : meatEaterMultiplicand;

        BigDecimal femaleMultiplicand = new BigDecimal("1.5");
        BigDecimal maleMultiplicand = new BigDecimal("1");
        BigDecimal sexMultiplicand = gender.equals("f") ? femaleMultiplicand : maleMultiplicand;

        BigDecimal strength = new BigDecimal(weight).multiply(dietMultiplicand).multiply(sexMultiplicand);
        return strength.setScale(0, RoundingMode.CEILING).intValue();
    }

    public boolean getIsFighting() {
        return isFighting;
    }

    public void setIsFighting(boolean fighting) {
        this.isFighting = fighting;
    }

    public void fight() {
        this.isFighting = true;
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
}
