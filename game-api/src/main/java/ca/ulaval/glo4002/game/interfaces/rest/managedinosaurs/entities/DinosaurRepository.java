package ca.ulaval.glo4002.game.interfaces.rest.managedinosaurs.entities;

import java.util.UUID;

public interface DinosaurRepository {
    void add(Dinosaur dinosaur);
    Dinosaur findById(UUID id);
}
