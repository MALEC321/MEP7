package ca.ulaval.glo4002.game.repositories.resources;

import ca.ulaval.glo4002.game.domain.resources.Resource;
import ca.ulaval.glo4002.game.domain.resources.ResourceElements;

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
