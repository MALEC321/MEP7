package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

public class DinosaurResponseItem {
    private String name;
    private int weight;
    private String gender;
    private String species;

    public DinosaurResponseItem(String name, int weight, String gender, String species) {
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.species = species;
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
