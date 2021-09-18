package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.UUID;

public class ActionFactory {

    public Actions create(String resource, Command command) {
        return (command == Command.ADD) ? new AddAction(UUID.randomUUID(), resource, command) :
                new RetrieveAction(UUID.randomUUID(), resource, command);
    }
}
