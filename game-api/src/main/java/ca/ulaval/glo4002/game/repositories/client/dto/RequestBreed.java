package ca.ulaval.glo4002.game.repositories.client.dto;

public class RequestBreed {
    private final String fatherSpecies;
    private final String motherSpecies;

    public RequestBreed(String fatherSpecies, String motherSpecies) {
        this.fatherSpecies = fatherSpecies;
        this.motherSpecies = motherSpecies;
    }

    public String getFatherSpecies() {
        return fatherSpecies;
    }

    public String getMotherSpecies() {
        return motherSpecies;
    }
}
