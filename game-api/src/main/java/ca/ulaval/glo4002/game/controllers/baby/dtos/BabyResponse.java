package ca.ulaval.glo4002.game.controllers.baby.dtos;

public class BabyResponse {
    private String name;
    private String fatherName;
    private String motherName;

    public BabyResponse(String name, String fatherName, String motherName) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
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
}
