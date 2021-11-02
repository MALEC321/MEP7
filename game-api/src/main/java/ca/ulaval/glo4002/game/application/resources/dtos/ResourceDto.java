package ca.ulaval.glo4002.game.application.resources.dtos;

public class ResourceDto {
    private int qtyBurger;
    private int qtySalad;
    private int qtyWater;

    public ResourceDto(int qtyBurger, int qtySalad, int qtyWater) {
        this.qtyBurger = qtyBurger;
        this.qtySalad = qtySalad;
        this.qtyWater = qtyWater;
    }

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
