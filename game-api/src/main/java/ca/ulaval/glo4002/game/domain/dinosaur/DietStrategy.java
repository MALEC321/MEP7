package ca.ulaval.glo4002.game.domain.dinosaur;

public interface DietStrategy {
    ResourcesStateDto calculateFoodNeeds(int weight, boolean isHungry);
}
