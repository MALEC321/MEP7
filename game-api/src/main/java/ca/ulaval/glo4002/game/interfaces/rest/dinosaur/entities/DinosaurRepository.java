package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities;

import java.util.List;

public interface DinosaurRepository {
    List<Dinosaur> findAll();

    Dinosaur findByName(String name);

    void save(Dinosaur dinosaur);

    List<Dinosaur> getSortedDinosaursByStrength();
}
