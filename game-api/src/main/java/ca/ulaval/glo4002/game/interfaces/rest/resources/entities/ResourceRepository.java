package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resource);
    void reset();
    boolean eatBurger(int quantityNeeded);
    boolean eatSalad(int quantity);
    boolean drinkWater(int quantity);
    void decreaseExpirationDate();
    int findResourceQuantity(int value);
    List<Resource> findAll();
    Resource findFreshResource();
    Resource findConsumedResource();
    Resource findExpiredResource();
}
