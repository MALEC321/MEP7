package ca.ulaval.glo4002.game.domain.actions;

import ca.ulaval.glo4002.game.domain.resources.Pantry;

public class DecreaseResourcesExpiration implements Action{

    private Pantry pantry;

    public DecreaseResourcesExpiration(Pantry pantry) {
        this.pantry = pantry;
    }

    @Override
    public void execute() {
        pantry.decreaseExpirationDate();
    }
}
