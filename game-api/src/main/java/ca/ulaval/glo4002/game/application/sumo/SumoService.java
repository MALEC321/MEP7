package ca.ulaval.glo4002.game.application.sumo;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

public class SumoService {
    private final HerdRepository herdRepository;
    private final ActionFactory actionFactory;
    private final ActionRepository actionRepository;

    public SumoService(HerdRepository herdRepository, ActionFactory actionFactory, ActionRepository actionRepository) {
        this.herdRepository = herdRepository;
        this.actionFactory = actionFactory;
        this.actionRepository = actionRepository;
    }

    public SumoResponse fight(SumoDto sumoDto) {
        Herd herd = herdRepository.findHerd();
        Dinosaur challenger = herd.findDinosaurByName(sumoDto.getChallenger());
        Dinosaur challengee = herd.findDinosaurByName(sumoDto.getChallengee());
        Action fightAction = actionFactory.createFight(actionRepository.getActionList(), challenger, challengee, herd);
        actionRepository.save(fightAction);
        return predictWinner(challenger, challengee);
    }

    public SumoResponse predictWinner(Dinosaur challenger, Dinosaur challengee) {
        SumoResponse response;
        if (challenger.getStrength() > challengee.getStrength()) {
            response = new SumoResponse(challenger.getName());
        } else if (challenger.getStrength() < challengee.getStrength()) {
            response = new SumoResponse(challengee.getName());
        } else {
            response = new SumoResponse("tie");
        }
        return response;
    }
}
