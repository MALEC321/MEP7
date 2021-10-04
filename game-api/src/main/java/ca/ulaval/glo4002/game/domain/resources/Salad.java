package ca.ulaval.glo4002.game.domain.resources;

import ca.ulaval.glo4002.game.domain.resources.ResourceElements;

public class Salad extends ResourceElements {
    private int expiration = 3;

    public Salad(int quantity) {
        super(quantity);
    }

    @Override
    public void decreaseExpirationDate() {
        expiration--;
    }

    @Override
    public int getDaysLeft() {
        return expiration;
    }
}
