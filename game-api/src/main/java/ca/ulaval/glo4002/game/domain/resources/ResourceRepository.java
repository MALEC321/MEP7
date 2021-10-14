package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resourceElements);
    void reset();
    Pantry getPantry();
    boolean removeResources(ResourceType type, int quantity);
    void decreaseExpirationDate();
    List<Resource> findAll();
}
