package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class Burger extends ResourceElements implements ResourceType{
    private static int expiration = 4;

    public Burger(int quantity) {
        super(quantity);
    }

    @Override
    public void consumeResource() {
        expiration--;
    }

    @Override
    public int getDaysLeft() {
        return expiration;
    }

    @Override
    public String getResourceName() {
        return super.getName();
    }

    @Override
    public int getResourceQty() {
        return super.getQuantity();
    }
}
