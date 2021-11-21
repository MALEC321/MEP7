package ca.ulaval.glo4002.game.controllers.resources.dtos;

public class ResourceRequest {
    private int qtyBurger;
    private int qtySalad;
    private int qtyWater;



    public ResourceRequest(int qtyBurger, int qtySalad, int qtyWater) {
        this.qtyBurger = qtyBurger;
        this.qtySalad = qtySalad;
        this.qtyWater = qtyWater;
    }

    public ResourceRequest() {}

    public int getQtyBurger() {
        return qtyBurger;
    }

    public int getQtySalad() {
        return qtySalad;
    }

    public int getQtyWater() {
        return qtyWater;
    }
}
