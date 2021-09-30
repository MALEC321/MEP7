package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidGenderException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidSpeciesException;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidWeightException;

import java.util.Objects;

public class DinosaurFactory {
    private final DinosaurRepository dinosaurRepository;

    public DinosaurFactory(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public Dinosaur create(String name, int weight, String gender, String species) {
        validateName(name);
        if (Objects.equals(species, "Tyrannosaurus Rex")) {
            species = "Tyrannosaurus";
        }

        if (weight <= 0) {
            throw new InvalidWeightException();
        }
        if ((!"f".equals(gender)) && (!"m".equals(gender))) {
            throw new InvalidGenderException();
        }
        if (!SpeciesDiet.contains(species)) {
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
