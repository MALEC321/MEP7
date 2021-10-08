package ca.ulaval.glo4002.game.domain.dinosaur;

public class BabyFactory {

    private final DinosaurRepository dinosaurRepository;

    public BabyFactory(DinosaurRepository dinosaurRepository){
        this.dinosaurRepository = dinosaurRepository;
    }

    //TODO enlever les variables hardcoder
    public DinosaurBaby create(String name, String fatherName, String motherName){
        return new DinosaurBaby(name, 1, "f", "Ankylosaurus", fatherName, motherName);
    }
}
