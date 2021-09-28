package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class DuplicateNameException extends HTTPExceptionItem {
    public DuplicateNameException() {
        super(400, "DUPLICATE_NAME", "The specified name already exists and must be unique.");
    }
}
