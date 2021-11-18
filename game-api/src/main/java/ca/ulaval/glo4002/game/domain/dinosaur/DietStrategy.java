package ca.ulaval.glo4002.game.domain.dinosaur;

public interface DietStrategy {
    OrderForm calculateFoodNeeds(int weight, boolean isHungry);
}
