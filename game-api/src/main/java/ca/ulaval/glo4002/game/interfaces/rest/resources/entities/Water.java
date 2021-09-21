package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public class Water extends ResourceElements implements ResourceType{
    private static int expiration = 10;

    public Water(int quantity) {
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
