package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class NotFoundNameException extends HTTPExceptionItem {
    public NotFoundNameException() {
        super(404, "NON_EXISTENT_NAME", "The specified name does not exist.");
    }
}
