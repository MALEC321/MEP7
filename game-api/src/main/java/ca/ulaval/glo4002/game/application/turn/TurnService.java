package ca.ulaval.glo4002.game.application.turn;

import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;

public class TurnService {
    private final TurnFactory turnFactory;
    private final GameRepository gameRepository;
    private final PantryRepository pantryRepository;
    private final HerdRepository herdRepository;
    private final ResourcesDistributor resourcesDistributor;
    private final ResourcesFactory resourcesFactory;
    private final ActionFactory actionFactory;

    public TurnService(
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
        Turn currentTurn = game.currentTurn();

        addTurnConsequences(currentTurn);
        currentTurn.executeActions();

        endTurn(game);
        gameRepository.save(game);
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

    private void addTurnConsequences(Turn currentTurn) {
        addCookItConsequence(currentTurn);
        addRemoveExpiredResourcesConsequence(currentTurn);
        addFeedDinosaursConsequence(currentTurn);
        addRemoveOrphanedBabyDinosaursConsequence(currentTurn);
    }

    private void addCookItConsequence(Turn currentTurn) {
        Pantry pantry = pantryRepository.findPantry();
        Action addCookIt = actionFactory.createCookItAction(pantry, resourcesFactory);
        currentTurn.addAction(addCookIt);
    }

    private void addRemoveExpiredResourcesConsequence(Turn currentTurn) {
        Pantry pantry = pantryRepository.findPantry();
        Action removeAllExpiredResourcesAction = actionFactory.createRemoveAllExpiredResourcesAction(pantry);
        currentTurn.addAction(removeAllExpiredResourcesAction);
    }

    private void addFeedDinosaursConsequence(Turn currentTurn) {
        Herd herd = herdRepository.findHerd();
        Pantry pantry = pantryRepository.findPantry();
        Action feedDinosaursAction = actionFactory.createFeedDinosaursAction(herdRepository, pantry, herd);
        currentTurn.addAction(feedDinosaursAction);
    }

    private void addRemoveOrphanedBabyDinosaursConsequence(Turn currentTurn) {
        Herd herd = herdRepository.findHerd();
        Action removeOrphanedBabyDinosaurs = actionFactory.createRemoveOrphanedBabyDinosaursAction(herd);
        currentTurn.addAction(removeOrphanedBabyDinosaurs);
    }

    private void endTurn(Game game) {
        Turn turn = turnFactory.create(game.nextTurnNumber());
        game.addTurn(turn);
    }
}
