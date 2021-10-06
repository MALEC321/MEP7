package ca.ulaval.glo4002.game.domain.dinosaur;

public class BabyFactory {

    private final DinosaurRepository dinosaurRepository;

    public BabyFactory(DinosaurRepository dinosaurRepository){
        this.dinosaurRepository = dinosaurRepository;
    }

    public DinosaurBaby create(String name, String fatherName, String motherName){
        return new DinosaurBaby(name, 1, null, null, fatherName, motherName);
    }
}
