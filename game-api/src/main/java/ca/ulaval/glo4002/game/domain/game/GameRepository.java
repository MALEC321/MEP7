package ca.ulaval.glo4002.game.domain.game;

public interface GameRepository {
    Game findGame();

    void deleteAll();

    void save(Game game);
}
