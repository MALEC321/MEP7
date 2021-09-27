package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.Queue;

public interface ResourceRepository {
    void add(ResourceElements resource);
    //Todo remove the public part
    boolean eatBurger(int Quantity);
    boolean eatSalad(int Quantity);
    boolean drinkWater(int Quantity);
    boolean consume(ResourceElements resourceElement, int Quantity);
    void removeStale();
    Queue<ResourceElements>  findAll();
}
