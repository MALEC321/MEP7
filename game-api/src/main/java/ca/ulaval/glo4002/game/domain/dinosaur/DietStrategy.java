package ca.ulaval.glo4002.game.domain.dinosaur;

public interface DietStrategy {
    PantryReport calculateFoodNeeds(int weight, boolean isHungry);
}
