package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.DuplicateNameException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidGenderException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidSpeciesException;
import ca.ulaval.glo4002.game.exceptions.types.InvalidWeightException;

public class DinosaurFactory {
    public static final int MIN_WEIGHT = 0;
    public static final int BABY_WEIGHT = 1;
    private final DinosaurRepository dinosaurRepository;
    private final SpeciesDietsCorrespondances speciesDietsCorrespondances;

    public DinosaurFactory(DinosaurRepository dinosaurRepository, SpeciesDietsCorrespondances speciesDietsCorrespondances) {
        this.dinosaurRepository = dinosaurRepository;
        this.speciesDietsCorrespondances = speciesDietsCorrespondances;
    }

    public Dinosaur create(String name, int weight, String gender, String species) {
        validateName(name);

        if (weight <= MIN_WEIGHT) {
            throw new InvalidWeightException();
        }
        if (!"f".equals(gender) && !"m".equals(gender)) {
            throw new InvalidGenderException();
        }

        if (!speciesDietsCorrespondances.dinosaurTypeExists(species)) {
            throw new InvalidSpeciesException();
        }

        return new Dinosaur(name, weight, gender, species);
    }

    public DinosaurBaby create(String name, String fatherName, String motherName, String gender, String species) {
        validateName(name);
        return new DinosaurBaby(name, BABY_WEIGHT, gender, species, fatherName, motherName);
    }

    public void validateName(String name) {
        if (dinosaurRepository.findByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
