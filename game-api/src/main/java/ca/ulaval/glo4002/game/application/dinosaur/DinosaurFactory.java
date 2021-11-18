package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.exceptions.DuplicateNameException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidGenderException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidSpeciesException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidWeightException;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;

public class DinosaurFactory {
    public static final int MIN_WEIGHT = 0;
    public static final int BABY_WEIGHT = 1;
    private final HerdRepository herdRepository;
    private final SpeciesDietsCorrespondances speciesDietsCorrespondances;

    public DinosaurFactory(HerdRepository herdRepository, SpeciesDietsCorrespondances speciesDietsCorrespondances) {
        this.herdRepository = herdRepository;
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

        return new Dinosaur(name, weight, gender, new DietStrategyFactory().create(species), species);
    }

    public Dinosaur create(String name, Dinosaur father, Dinosaur mother, String gender, String species) {
        validateName(name);
        return new Dinosaur(name, BABY_WEIGHT, gender, new DietStrategyFactory().create(species), mother, father, species);
    }

    public void validateName(String name) {
        Herd herd = herdRepository.findHerd();
        if (herd.findDinosaurByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
