package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

public class Dinosaur {
    public final String name;
    public final int weight;
    public final String gender;
    public final String species;
    private final double force;
    private final String diet;

    public Dinosaur(String name, int weight, String gender, String species, String diet) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
        this.diet = diet;
        this.force = calculForce(weight, gender, diet);
    }

    private double calculForce(int weight, String gender, String diet) {
        double T = (diet.equals("Carnivore")) ? 1.5 : 1;
        double S = (gender.equals("f")) ? 1.5 : 1;
        return weight * T * S;
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
}
