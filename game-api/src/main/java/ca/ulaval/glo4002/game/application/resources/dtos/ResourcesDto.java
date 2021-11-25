package ca.ulaval.glo4002.game.application.resources.dtos;

public class ResourcesDto {
    private final int qtyBurger;
    private final int qtySalad;
    private final int qtyWater;

    public ResourcesDto(int qtyBurger, int qtySalad, int qtyWater) {
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
