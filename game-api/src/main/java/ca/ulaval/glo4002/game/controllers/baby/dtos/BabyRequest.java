package ca.ulaval.glo4002.game.controllers.baby.dtos;

public class BabyRequest {
    private String name;
    private String fatherName;
    private String motherName;

    public BabyRequest(String name, String fatherName, String motherName) {
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public BabyRequest() {}

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
