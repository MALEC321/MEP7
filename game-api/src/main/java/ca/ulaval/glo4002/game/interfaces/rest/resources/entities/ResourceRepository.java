package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resource);
    void reset();
    boolean consume(ResourceElements resourceElement, int Quantity);
    void decreaseExpirationDate();
    int findResourceQuantity(int value);
    List<Resource> findAll();
    Resource findFreshResource();
    Resource findConsumedResource();
    Resource findExpiredResource();
}
