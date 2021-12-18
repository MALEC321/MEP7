package ca.ulaval.glo4002.game.domain.game;

public interface GameRepository {
    Game findCurrent();

    void deleteAll();

    void save(Game game);

    Game findPrevious();

    public void deleteLast();
}
