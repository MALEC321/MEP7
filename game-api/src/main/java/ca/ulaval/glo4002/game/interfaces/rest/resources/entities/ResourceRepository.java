package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

public interface ResourceRepository {
    void add(Resource resource);
    void removeStale();
}
