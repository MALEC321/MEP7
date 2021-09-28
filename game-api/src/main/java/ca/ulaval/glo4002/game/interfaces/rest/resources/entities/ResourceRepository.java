package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import org.javatuples.Triplet;

import java.util.Queue;

public interface ResourceRepository {
    void add(ResourceElements resource);
    boolean consume(ResourceElements resourceElement, int Quantity);
    void decreaseExpirationDate();
    Triplet<Queue<Burger>, Queue<Salad>, Queue<Water>> findAll();
}
