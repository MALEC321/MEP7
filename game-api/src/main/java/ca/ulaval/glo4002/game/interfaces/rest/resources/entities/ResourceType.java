package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public interface ResourceType {
    int getDaysLeft();

    int getResourceQty();

    void consumeResource();
}
