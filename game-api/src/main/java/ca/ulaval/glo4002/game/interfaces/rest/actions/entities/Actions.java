package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.UUID;

public abstract class Actions {
    private final UUID id;
    private final Object object; //Todo change this to type Resource
    private final Object repository;

    public Actions(UUID id, Object object, Object repository) {
        this.id = id;
        this.object = object;
        this.repository = repository;
    }

    public UUID getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }

    public Object getRepository() {
        return repository;
    }

    abstract public void execute();
}
