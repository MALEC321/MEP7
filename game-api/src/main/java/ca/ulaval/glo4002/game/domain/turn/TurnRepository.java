package ca.ulaval.glo4002.game.domain.turn;

import java.util.UUID;

public interface TurnRepository {
    Turn findById(UUID id);
    void reset();

    void save(Turn turn);
}
