package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    @Override
    public Dinosaur findByName(String name) {
        return dinosaursByName.get(name);
    }

    @Override public void save(Dinosaur dinosaur) {
        dinosaursByName.put(dinosaur.getName(), dinosaur);
    }
}
