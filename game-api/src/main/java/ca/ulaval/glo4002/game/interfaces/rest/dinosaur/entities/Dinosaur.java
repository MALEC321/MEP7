package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import java.util.UUID;

public class Dinosaur {
    private final UUID id;
    private final String name;
    public int weight;
    private final String gender;
    private final String species;

    public Dinosaur(UUID id, String name, int weight, String gender, String species) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
    }

    public UUID getId() {
        return id;
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
}
