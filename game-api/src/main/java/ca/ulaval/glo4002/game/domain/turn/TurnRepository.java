package ca.ulaval.glo4002.game.domain.turn;

public interface TurnRepository {
    Game findGame();

    void deleteAll();

    void save(Game game);
}
