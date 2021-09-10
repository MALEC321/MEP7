package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.UUID;

public class RetrieveAction extends Actions {
    public RetrieveAction(UUID id, String resource, Command command) {
        super(id, resource, command);
    }

    @Override
    public void execute() {
        System.out.println("Get resource in repository and retrieve a value of it");
    }
}
