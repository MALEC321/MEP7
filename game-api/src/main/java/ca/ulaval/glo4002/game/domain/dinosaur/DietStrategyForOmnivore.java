package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

public class DietStrategyForOmnivore extends AbstractDietStrategy {
    private final DietStrategyForCarnivore dietStrategyForCarnivore;
    private final DietStrategyForHerbivore dietStrategyForHerbivore;

    public DietStrategyForOmnivore(DietType dietType) {
        super(dietType);
        this.dietStrategyForCarnivore = new DietStrategyForCarnivore(dietType);
        this.dietStrategyForHerbivore = new DietStrategyForHerbivore(dietType);
    }

    @Override
    public PantryReport calculateFoodNeeds(int weight, boolean isHungry) {
        PantryReport foodNeededForCarnivore = dietStrategyForCarnivore.calculateFoodNeeds(weight, isHungry);
        PantryReport foodNeededForHerbivore = dietStrategyForHerbivore.calculateFoodNeeds(weight, isHungry);
        return foodNeededForCarnivore.addPantryReport(foodNeededForHerbivore);
    }
}
