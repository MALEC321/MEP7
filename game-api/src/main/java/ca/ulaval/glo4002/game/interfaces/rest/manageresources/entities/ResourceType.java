package ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities;

public interface ResourceType {
    int getDaysLeft();

    String getResourceName();

    int getResourceQty();

    void consumeResource();
}
