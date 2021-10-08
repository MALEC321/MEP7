package ca.ulaval.glo4002.game.domain.dinosaur;

public class DinosaurBaby extends Dinosaur {

    private final String fatherName;
    private final String motherName;

    private String type;

    public DinosaurBaby(String name, int weight, String gender, String species, String fatherName, String motherName) {
        super(name, weight, gender, species);
        this.fatherName = fatherName;
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public String getMotherName() {
        return motherName;
    }
}
