package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.UUID;

public abstract class Actions {
    private final UUID id;
    private final String resource; //Todo change this to type Resource
    private final Command command;

    public Actions(UUID id, String resource, Command command) {
        this.id = id;
        this.resource = resource;
        this.command = command;
    }

    public UUID getId() {
        return id;
    }

    public String getResource() {
        return resource;
    }

    public Command getCommand() {
        return command;
    }

    abstract public void execute();
}
