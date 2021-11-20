package ca.ulaval.glo4002.game.controllers.sumo.dtos;

public class SumoDto {
    private final String challenger;
    private final String challengee;

    public SumoDto(String challenger, String challengee) {
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
