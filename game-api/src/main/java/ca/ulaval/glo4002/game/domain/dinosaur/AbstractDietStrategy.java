package ca.ulaval.glo4002.game.domain.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;

public abstract  class AbstractDietStrategy implements DietStrategy {
    private final DietType dietType;
    public AbstractDietStrategy(DietType dietType) {
        this.dietType = dietType;
    }

    public DietType getDietType() {
        return dietType;
    }
}
