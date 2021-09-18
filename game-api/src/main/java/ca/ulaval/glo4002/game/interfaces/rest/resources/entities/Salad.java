package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class Salad extends ResourceElements implements ResourceType{
    private static int expiration = 3;

    public Salad(int quantity) {
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
    public int getResourceQty() {
        return super.getQuantity();
    }
}
