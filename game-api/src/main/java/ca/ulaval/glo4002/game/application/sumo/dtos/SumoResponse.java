package ca.ulaval.glo4002.game.application.sumo.dtos;

public class SumoResponse {
    private final String predictedWinner;

    public SumoResponse(String predictedWinner) {
        this.predictedWinner = predictedWinner;
    }

    public String getPredictedWinner() {
        return predictedWinner;
    }
}
