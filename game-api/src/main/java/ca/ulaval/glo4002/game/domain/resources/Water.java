package ca.ulaval.glo4002.game.domain.resources;

public class Water extends Resource {
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

    @Override
    public boolean isExpired() {
        return expiration == 0;
    }
}
