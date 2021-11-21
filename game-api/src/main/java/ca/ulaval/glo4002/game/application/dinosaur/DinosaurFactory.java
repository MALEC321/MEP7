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
    public static final int MIN_ADULT_WEIGHT = 0;
    public static final int DEFAULT_BABY_WEIGHT = 1;
    private final HerdRepository herdRepository;
    private final SpeciesDietsCorrespondances speciesDietsCorrespondances;
    private final DietStrategyFactory dietStrategyFactory;

    public DinosaurFactory(HerdRepository herdRepository, SpeciesDietsCorrespondances speciesDietsCorrespondances) {
        this.herdRepository = herdRepository;
        this.speciesDietsCorrespondances = speciesDietsCorrespondances;
        this.dietStrategyFactory = new DietStrategyFactory();
    }

    public Dinosaur createDinosaur(String name, int weight, String gender, String species) {
        validateName(name);
        validateWeight(weight);
        validateGender(gender);
        validateSpecies(species);
        return new Dinosaur(name, weight, gender, dietStrategyFactory.create(species), species);
    }

    public Dinosaur createBabyDinosaur(String name, Dinosaur father, Dinosaur mother, String gender, String species) {
        validateName(name);
        return new Dinosaur(name, DEFAULT_BABY_WEIGHT, gender, dietStrategyFactory.create(species), mother, father, species);
    }

    private void validateSpecies(String species) {
        if (!speciesDietsCorrespondances.dinosaurTypeExists(species)) {
            throw new InvalidSpeciesException();
        }
    }

    private void validateGender(String gender) {
        if (!"f".equals(gender) && !"m".equals(gender)) {
            throw new InvalidGenderException();
        }
    }

    private void validateWeight(int weight) {
            if (weight < MIN_ADULT_WEIGHT) {
            throw new InvalidWeightException();
        }
    }

    private void validateName(String name) {
        Herd herd = herdRepository.findHerd();
        if (herd.findDinosaurByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}