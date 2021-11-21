package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

public class DinosaurRequest {
    private String name;
    private int weight;
    private String gender;
    private String species;

    public DinosaurRequest(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
    }

    public DinosaurRequest() {}

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
