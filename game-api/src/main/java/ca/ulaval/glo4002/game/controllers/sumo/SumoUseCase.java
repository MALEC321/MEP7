package ca.ulaval.glo4002.game.controllers.sumo;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurUseCase;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

public class SumoUseCase {
    private final HerdRepository herdRepository;
    private final ActionFactory actionFactory;
    private final ActionRepository actionRepository;
    private final DinosaurUseCase dinosaurUseCase;

    public SumoUseCase(HerdRepository herdRepository, ActionFactory actionFactory, ActionRepository actionRepository, DinosaurUseCase dinosaurUseCase) {
        this.herdRepository = herdRepository;
        this.actionFactory = actionFactory;
        this.actionRepository = actionRepository;
        this.dinosaurUseCase = dinosaurUseCase;
    }

    public SumoResponse fight(SumoDto sumoDto) {
        Herd herd = herdRepository.findHerd();
        Dinosaur challenger = herd.findDinosaurByName(sumoDto.challenger);
        Dinosaur challengee = herd.findDinosaurByName(sumoDto.challengee);
        Action fightAction = actionFactory.fight(actionRepository.getActionList(), challenger, challengee, herd);
        actionRepository.save(fightAction);

        SumoResponse response = new SumoResponse();
        if (challenger.getStrength() > challengee.getStrength()) {
            response.predictedWinner = challenger.getName();
        }
        else if (challenger.getStrength() < challengee.getStrength()) {
            response.predictedWinner = challengee.getName();
        }
        else
            response.predictedWinner = "tie";
        return response;
    }
}
