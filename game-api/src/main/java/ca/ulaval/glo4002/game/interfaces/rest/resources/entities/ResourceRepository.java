package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import org.javatuples.Triplet;

import java.util.List;
import java.util.Queue;

public interface ResourceRepository {
    void add(ResourceElements resource);
    boolean consume(ResourceElements resourceElement, int Quantity);
    void decreaseExpirationDate();
    int findResourceQuantity(int value);
    List<Resource> findAll();
    Resource findFreshResource();
    Resource findConsumedResource();
    Resource findExpiredResource();
}
