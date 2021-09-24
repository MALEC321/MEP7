package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.Queue;

public interface ResourceRepository {
    void add(ResourceElements resource);
    void removeStale();
    Queue<ResourceElements>  findAll();
}
