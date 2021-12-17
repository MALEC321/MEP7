package ca.ulaval.glo4002.game.domain.game;

public interface GameRepository {
    Game findCurrentGame();

    void deleteAll();

    void save(Game game);

    Game findPreviousGame();

    void deleteLast();
}
