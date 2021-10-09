package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.List;

public interface DinosaurRepository {
    List<Dinosaur> findAll();

    Dinosaur findByName(String name);

    void save(Dinosaur dinosaur);

    void remove(Dinosaur dinosaur);

    List<Dinosaur> getSortedDinosaursByStrengthThenName();

    void reset();

}
