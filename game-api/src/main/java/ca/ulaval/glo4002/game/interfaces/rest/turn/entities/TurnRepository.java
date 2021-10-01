package ca.ulaval.glo4002.game.interfaces.rest.turn.entities;

import java.util.UUID;

public interface TurnRepository {
    Turn findById(UUID id);
    void reset();

    void save(Turn turn);
}
