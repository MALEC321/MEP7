package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

public class DietStrategyForOmnivore extends AbstractDietStrategy {
    private final DietStrategyForCarnivore dietStrategyForCarnivore;
    private final DietStrategyForHerbivore dietStrategyForHerbivore;
    private boolean eatingInHerbivoreResources;

    public DietStrategyForOmnivore(DietType dietType) {
        super(dietType);
        this.dietStrategyForCarnivore = new DietStrategyForCarnivore(dietType);
        this.dietStrategyForHerbivore = new DietStrategyForHerbivore(dietType);
        eatingInHerbivoreResources = true;
    }

    @Override
    public ResourcesStateDto calculateFoodNeeds(int weight, boolean isHungry) {
        if (eatingInHerbivoreResources) {
            eatingInHerbivoreResources = false;
            return dietStrategyForHerbivore.calculateFoodNeeds(weight, isHungry);
        } else {
            eatingInHerbivoreResources = true;
            return dietStrategyForCarnivore.calculateFoodNeeds(weight, isHungry);
        }
    }
}
