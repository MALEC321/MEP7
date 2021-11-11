package ca.ulaval.glo4002.game.domain.turn;

public interface TurnRepository {
    Turns findTurns();

    void reset();

    void save(Turns turns);
}
