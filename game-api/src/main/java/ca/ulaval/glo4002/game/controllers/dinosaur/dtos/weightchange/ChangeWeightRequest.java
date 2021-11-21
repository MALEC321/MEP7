package ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange;

public class ChangeWeightRequest {
    private int weight;

    public ChangeWeightRequest() {}

    public ChangeWeightRequest(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
