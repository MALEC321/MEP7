package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.List;

public interface HerdRepository {
    Herd findHerd();

    void save(Herd herd);

    void reset();
}
