package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class InvalidResourceQuantityException extends HTTPExceptionItem {
    public InvalidResourceQuantityException() {
        super(400, "INVALID_RESOURCE_QUANTITY", "Resource quantities must be positive.");
    }
}
