package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public abstract class ResourceElements {
    private int quantity;

    public ResourceElements(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean removeElement(int quantity) {
        int actualQuantity = this.quantity;
        this.quantity = (quantity <= this.quantity) ? this.quantity - quantity : 0;
        return quantity <= actualQuantity;
    }

    public void reset() {
        this.quantity = 0;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public abstract int getDaysLeft();
    public abstract void decreaseExpirationDate();
}
