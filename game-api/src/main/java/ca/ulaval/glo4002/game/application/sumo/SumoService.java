package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;

public class SumoService {
    private final HerdRepository herdRepository;
    private final ActionFactory actionFactory;
    private final GameRepository gameRepository;

    public SumoService(HerdRepository herdRepository, ActionFactory actionFactory, GameRepository gameRepository) {
        this.herdRepository = herdRepository;
        this.actionFactory = actionFactory;
        this.gameRepository = gameRepository;
    }

    public SumoResponse fight(SumoDto sumoDto) {
        Herd herd = herdRepository.findCurrent();
        Game game = gameRepository.findCurrentGame();
        Turn turn = game.currentTurn();
        Dinosaur challenger = herd.findDinosaurByName(sumoDto.getChallenger());
        Dinosaur challengee = herd.findDinosaurByName(sumoDto.getChallengee());
        Action fightAction = actionFactory.createFight(turn.getActions(), challenger, challengee, herd);

        turn.addAction(fightAction);
        gameRepository.save(game);
        return new SumoResponse(herd.fight(challenger, challengee, false));
    }
}
