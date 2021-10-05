package ca.ulaval.glo4002.game.domain.bebe;

public class Bebe {

    private final String name;
    private final String fatherName;
    private final String motherName;

    public Bebe(String name, String fatherName, String motherName) {
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
