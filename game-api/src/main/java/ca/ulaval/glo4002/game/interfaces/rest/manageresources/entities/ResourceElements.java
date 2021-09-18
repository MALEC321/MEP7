package ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities;

public class ResourceElements {
    private int quantity;
    private String name;

    public ResourceElements(int quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
