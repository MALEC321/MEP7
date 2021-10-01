package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

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
