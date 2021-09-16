package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final Map<UUID, Dinosaur> dinosaursById = new HashMap<>();

    @Override
    public Dinosaur findById(UUID id) {
        return dinosaursById.get(id);
    }

    @Override public void save(Dinosaur dinosaur) {
        dinosaursById.put(dinosaur.getId(), dinosaur);
    }
}
