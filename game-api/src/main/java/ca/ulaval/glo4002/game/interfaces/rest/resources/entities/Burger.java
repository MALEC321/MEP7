package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class Burger extends ResourceElements {
    private int expiration = 4;

    public Burger(int quantity) {
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
