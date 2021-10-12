package ca.ulaval.glo4002.game.domain.resources;

public interface Eatable {
    boolean remove(int quantity, ResourceType resourceType);
}
