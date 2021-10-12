package ca.ulaval.glo4002.game.domain.resources;

import java.util.List;

public interface ResourceRepository {
    void add(ResourceElements resourceElements);
    void reset();
    Pantry getPantry();
    boolean removeBurgers(int quantity);
    boolean removeSalads(int quantity);
    boolean removeWater(int quantity);
    void decreaseExpirationDate();
    List<Resource> findAll();
}
