package ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities;

public class Salad extends ResourceElements implements ResourceType{
    private static int expiration = 3;

    public Salad(int quantity, String name) {
        super(quantity, name);
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
