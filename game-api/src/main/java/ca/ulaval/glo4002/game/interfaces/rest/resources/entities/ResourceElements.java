package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public abstract class ResourceElements {
    private int quantity;

    public ResourceElements(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
