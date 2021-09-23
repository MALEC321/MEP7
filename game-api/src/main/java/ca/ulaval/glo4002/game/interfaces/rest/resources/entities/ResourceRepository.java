package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.Queue;

public interface ResourceRepository {
    void add(ResourceElements resource);
    boolean eatBurger(int Quantity);
    boolean eatSalad(int Quantity);
    boolean drinkWater(int Quantity);
    void removeStale();
    Queue<ResourceElements>  findAll();
}
