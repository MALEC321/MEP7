package ca.ulaval.glo4002.game.application.baby.dtos;

public class ExternalApiBabyRequest {
    private final String name;
    private final String fatherName;
    private final String motherName;
    private final String gender;
    private final String species;

    public ExternalApiBabyRequest(String name, String fatherName, String motherName, String gender, String species) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.gender = gender;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public String getGender() {
        return gender;
    }

    public String getSpecies() {
        return species;
    }
}
