package ca.ulaval.glo4002.game.domain.bebe;

public class BebeFactory {

    private final BebeRepository bebeRepository;

    public BebeFactory(BebeRepository bebeRepository){
        this.bebeRepository = bebeRepository;
    }

    public Bebe create(String name, String fatherName, String motherName){
        return new Bebe(name, fatherName, motherName);
    }
}
