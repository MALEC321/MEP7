package ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange;

public class ChangeWeightDto {
    private final String dinoName;
    private final int weightChange;

    public ChangeWeightDto(String dinoName, int weightChange) {
        this.dinoName = dinoName;
        this.weightChange = weightChange;
    }

    public String getDinoName() {
        return dinoName;
    }

    public int getWeightChange() {
        return weightChange;
    }
}
