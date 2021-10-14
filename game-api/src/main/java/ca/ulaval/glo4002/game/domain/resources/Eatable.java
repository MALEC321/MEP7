package ca.ulaval.glo4002.game.domain.resources;

public interface Eatable {
    boolean removeResource(ResourceType resourceType, int quantity);
}
