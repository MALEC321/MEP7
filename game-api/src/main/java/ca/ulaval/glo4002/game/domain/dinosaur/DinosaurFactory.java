package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;

public class DinosaurFactory {
    public static final int MIN_WEIGHT = 0;
    private final DinosaurRepository dinosaurRepository;
    private final SpeciesDietsCorrespondances speciesDietsCorrespondances;

    public DinosaurFactory(DinosaurRepository dinosaurRepository, SpeciesDietsCorrespondances speciesDietsCorrespondances) {
        this.dinosaurRepository = dinosaurRepository;
        this.speciesDietsCorrespondances = speciesDietsCorrespondances;
    }



    public DinosaurAdult create(String name, int weight, String gender, String species) {
        validateName(name);

        if (weight <= MIN_WEIGHT) {
            throw new InvalidWeightException();
        }
        if ((!"f".equals(gender)) && (!"m".equals(gender))) {
            throw new InvalidGenderException();
        }

        if(!speciesDietsCorrespondances.dinosaurExists(species)){
            throw new InvalidSpeciesException();
        }

        return new DinosaurAdult(name, weight, gender, species);
    }

    public DinosaurBaby create(String name, String fatherName, String motherName){
        validateName(name);
        return new DinosaurBaby(name, 1, "f", "Ankylosaurus", fatherName, motherName);
    }


    public void validateName(String name) {
        if (dinosaurRepository.findByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
