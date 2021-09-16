package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import java.util.UUID;

public interface DinosaurRepository {
    Dinosaur findById(UUID id);
    void save(Dinosaur dinosaur);
}
