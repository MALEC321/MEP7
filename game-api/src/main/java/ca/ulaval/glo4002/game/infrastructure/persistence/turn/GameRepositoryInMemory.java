package ca.ulaval.glo4002.game.infrastructure.persistence.turn;

import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import java.util.ArrayList;
import java.util.List;

public class GameRepositoryInMemory implements GameRepository {
    private List<Game> games = new ArrayList<>();

    public GameRepositoryInMemory() {
        this.games.add(new Game());
    }

    @Override
    public void save(Game game) {
        this.games.add(game);
    }

    @Override
    public Game findCurrentGame() {
        return this.games.get(games.size()-1);
    }

    @Override
    public void deleteAll() {
        games = new ArrayList<>();
    }

    @Override
    public Game findPreviousGame() {
        return this.games.get(games.size()-2);
    }

    @Override
    public void deleteLast() {
        games.remove(games.size()-1);
    }
}
