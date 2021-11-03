package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.List;

public interface DinosaurRepository {
    Herd findHerd();

    void add(Herd herd);

    void reset();
}
