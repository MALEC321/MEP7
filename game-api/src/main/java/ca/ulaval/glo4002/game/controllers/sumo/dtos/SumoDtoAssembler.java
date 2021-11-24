package ca.ulaval.glo4002.game.controllers.sumo.dtos;

import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;

public class SumoDtoAssembler {
    public SumoDto fromRequest(SumoRequest sumoRequest) {
        return new SumoDto(sumoRequest.getChallenger(), sumoRequest.getChallengee());
    }
}
