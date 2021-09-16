package ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities;

import java.util.UUID;

public class Resource {
    private UUID id;
    private int qtyWater;
    private int qtySalads;
    private int qtyBurgers;

    public Resource(UUID id, int qtyWater, int qtySalads, int qtyBurgers) {
        this.id = id;
        this.qtyWater = qtyWater;
        this.qtySalads = qtySalads;
        this.qtyBurgers = qtyBurgers;
    }

    public UUID getId() {
        return id;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public int getQtyWater() {
        return qtyWater;
    }

    public void setQtyWater(int qtyWater) {
        this.qtyWater = qtyWater;
    }

    public int getQtySalads() {
        return qtySalads;
    }

    public void setQtySalads(int qtySalads) {
        this.qtySalads = qtySalads;
    }

    public int getQtyBurgers() {
        return qtyBurgers;
    }

    public void setQtyBurgers(int qtyBurgers) {
        this.qtyBurgers = qtyBurgers;
    }
}
