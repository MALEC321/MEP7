package ca.ulaval.glo4002.game.interfaces.rest.managedinosaurs.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.managedinosaurs.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.managedinosaurs.entities.DinosaurRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final Map<UUID, Dinosaur> inventory = new HashMap<>();

    @Override
    public void add(Dinosaur dinosaur) {
        inventory.put(dinosaur.getId(), dinosaur);
    }

    @Override
    public Dinosaur findById(UUID id) {
        return inventory.get(id);
    }
}
