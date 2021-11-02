package ca.ulaval.glo4002.game.controllers.baby.dtos;

public class ExternalApiCreationDto {
    private String fatherSpecies;
    private String motherSpecies;

    public ExternalApiCreationDto(String fatherSpecies, String motherSpecies) {
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
