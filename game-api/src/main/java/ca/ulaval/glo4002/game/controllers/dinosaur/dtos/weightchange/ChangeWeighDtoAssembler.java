package ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange;

public class ChangeWeighDtoAssembler {
    public ChangeWeightDto fromRequest(String dinoName, ChangeWeightRequest changeWeightRequest) {
        return new ChangeWeightDto(dinoName, changeWeightRequest.getWeight());
    }
}
