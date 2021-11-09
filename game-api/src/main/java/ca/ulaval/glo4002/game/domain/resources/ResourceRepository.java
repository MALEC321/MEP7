package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface ResourceRepository {
    void add(Resources resources);

    void reset();

    Pantry getPantry();

    int getFreshResourceQuantity(ResourceType resourceType);

    boolean removeResources(ResourceType type, int quantity);

    void decreaseExpirationDate();

    List<ResourcesGroup> findAll();
}
