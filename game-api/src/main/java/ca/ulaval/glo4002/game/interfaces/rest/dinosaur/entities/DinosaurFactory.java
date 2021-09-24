package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDiet;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.DuplicateNameException;

public class DinosaurFactory {
    private final DinosaurRepository dinosaurRepository;

    public DinosaurFactory(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }

    public Dinosaur create(String name, int weight, String gender, String species) {
        validateName(name);
        return new Dinosaur(name, weight, gender, species, SpeciesDiet.valueOf(species).name());
    }

    private void validateName(String name) {
        if (dinosaurRepository.findByName(name) != null) {
            throw new DuplicateNameException();
        }
    }
}
