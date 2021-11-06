package ca.ulaval.glo4002.game.domain.turn;

import ca.ulaval.glo4002.game.domain.turn.aggregate.TurnId;

import java.util.UUID;

public interface TurnRepository {
    TurnId findById(String turnId);

    void reset();

    void save(TurnId turnId);

    String nextTurnId();

    int numberOfTurns();
}
