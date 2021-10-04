package ca.ulaval.glo4002.game.domain.actions;

import java.util.UUID;

public abstract class Action {
    private final UUID id;
    private final Object object;
    private final Object objectRepository;

    public Action(UUID id, Object object, Object objectRepository) {
        this.id = id;
        this.object = object;
        this.objectRepository = objectRepository;
    }

    public UUID getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }

    public Object getObjectRepository() {
        return objectRepository;
    }

    abstract public void execute();
}
