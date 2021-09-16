package ca.ulaval.glo4002.game.interfaces.rest.managedinosaurs.entities;

import java.util.UUID;

public class Dinosaur {
    private UUID id;
    private String name;
    private int weight;
    private String gender;
    private String species;

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

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
