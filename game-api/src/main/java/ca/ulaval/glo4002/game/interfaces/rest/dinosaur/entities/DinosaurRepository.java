package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

public interface DinosaurRepository {
    Dinosaur findByName(String name);
    void save(Dinosaur dinosaur);
}
