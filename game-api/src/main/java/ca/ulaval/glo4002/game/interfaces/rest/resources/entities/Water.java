package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class Water extends ResourceElements {
    private int expiration = 10;

    public Water(int quantity) {
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
