package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidGenderException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidSpeciesException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidWeightException;

import java.util.Objects;

public class DinosaurFactory {
    public static final int MIN_WEIGHT = 0;
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
        if ((!"f".equals(gender)) && (!"m".equals(gender))) {
            throw new InvalidGenderException();
        }

        if(!speciesDietsCorrespondances.dinosaurExists(species)){
            throw new InvalidSpeciesException();
        }

        return new Dinosaur(name, weight, gender, species, SpeciesDiet.valueOf(species).name());
    }

    public void validateName(String name) {
        if (dinosaurRepository.findByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
