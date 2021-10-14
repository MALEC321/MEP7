package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resourceElements);
    void reset();
    boolean removeResources(ResourceTypesEnum type, int quantity);
    void decreaseExpirationDate();
    List<Resource> findAll();
}
