package ca.ulaval.glo4002.game.controllers.sumo.dtos;

public class SumoRequest {
    private String challenger;
    private String challengee;

    public SumoRequest(String challenger, String challengee) {
        this.challenger = challenger;
        this.challengee = challengee;
    }

    public String getChallenger() {
        return challenger;
    }

    public String getChallengee() {
        return challengee;
    }
}