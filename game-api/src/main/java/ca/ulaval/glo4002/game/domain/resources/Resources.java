package ca.ulaval.glo4002.game.domain.resources;

public class Resource {
    private ResourceType type;
    private int quantity;
    private int expiration;

    public Resource(ResourceType type, int quantity, int expiration) {
        this.type = type;
        this.quantity = quantity;
        this.expiration = expiration;
    }

    public ResourceType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEmpty() {
        return quantity == 0;
    }

    public boolean removeElement(int quantity) {
        int actualQuantity = this.quantity;
        this.quantity = (quantity <= this.quantity) ? this.quantity - quantity : 0;

        return quantity <= actualQuantity;
    }

    public void reset() {
        this.quantity = 0;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getDaysLeft() {
        return expiration;
    }

    public void decreaseExpirationDate() {
        expiration--;
    }

    public boolean isExpired() {
        return expiration == 0;
    }
}
