package ca.ulaval.glo4002.game.interfaces.rest.actions.entities;

import java.util.UUID;

public class AddAction extends Actions{

    public AddAction(UUID id, String resource, Command command) {
        super(id, resource, command);
    }

    @Override
    public void execute() {
        System.out.println("Get resource in repository and add a value to it");
    }
}
