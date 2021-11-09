package ca.ulaval.glo4002.game.application.dinosaur.dtos;

public class DinosaurDto {
    private final String name;
    private final int weight;
    private final String gender;
    private final String species;

    public DinosaurDto(String name, int weight, String gender, String species) {
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
