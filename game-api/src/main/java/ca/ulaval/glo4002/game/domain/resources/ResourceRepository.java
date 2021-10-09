package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resourceElements);
    void reset();
    boolean eatBurger(int quantity);
    boolean eatSalad(int quantity);
    boolean drinkWater(int quantity);
    void decreaseExpirationDate();
    List<Resource> findAll();
}
