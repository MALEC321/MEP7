package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

public class TurnUseCase {
    private final TurnFactory turnFactory;
    private final GameRepository gameRepository;
    private final PantryRepository pantryRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;
    private final ActionFactory actionFactory;

    public TurnUseCase(
            TurnFactory turnFactory,
            GameRepository gameRepository,
            PantryRepository pantryRepository,
            HerdRepository herdRepository,
            ResourcesDistributor resourcesDistributor,
            ResourcesFactory resourcesFactory,
            ActionFactory actionFactory) {
        this.turnFactory = turnFactory;
        this.gameRepository = gameRepository;
        this.pantryRepository = pantryRepository;
        this.herdRepository = herdRepository;
        this.resourcesDistributor = resourcesDistributor;
        this.resourcesFactory = resourcesFactory;
        this.actionFactory = actionFactory;
    }

    public void playTurn() {
        Game game = gameRepository.findGame();

        addCookItConsequences(game);
        decreaseResourcesExpirationDate(game);
        feedDinosaurs(game);
        removeOrphanedBabyDinosaurs(game);

        Turn currentTurn = game.currentTurn();
        currentTurn.executeActions();

        endTurn(game);
        gameRepository.save(game);
    }

    protected void addCookItConsequences(Game game) {
        Pantry pantry = pantryRepository.findPantry();
        Action addCookIt = actionFactory.create(pantry, resourcesFactory);
        Turn currentTurn = game.currentTurn();
        currentTurn.addAction(addCookIt);
    }

    protected void decreaseResourcesExpirationDate(Game game) {
        Pantry pantry = pantryRepository.findPantry();
        Action decreaseExpirationDate = actionFactory.create(pantry);
        Turn currentTurn = game.currentTurn();
        currentTurn.addAction(decreaseExpirationDate);
    }

    protected void feedDinosaurs(Game game) {
        Action feedDinosaurs = actionFactory.create(resourcesDistributor, pantryRepository.findPantry(), herdRepository.findHerd());
        Turn currentTurn = game.currentTurn();
        currentTurn.addAction(feedDinosaurs);
    }

    protected void removeOrphanedBabyDinosaurs(Game game) {
        Action removeOrphanedBabyDinosaurs = actionFactory.create(herdRepository.findHerd());
        Turn currentTurn = game.currentTurn();
        currentTurn.addAction(removeOrphanedBabyDinosaurs);
    }

    public TurnNumber getLastPlayedTurnNumber() {
        Game game = gameRepository.findGame();
        return game.lastPlayedTurnNumber();
    }

    public void resetGame() {
        gameRepository.deleteAll();
        pantryRepository.deleteAll();
        herdRepository.deleteAll();
    }

    private void endTurn(Game game) {
        Turn turn = turnFactory.create(game.nextTurnNumber());
        game.addTurn(turn);
    }
}
