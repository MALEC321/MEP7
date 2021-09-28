package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class InvalidWeightException extends HTTPExceptionItem {
    public InvalidWeightException() {
        super(400, "INVALID_WEIGHT", "The specified weight must be greater than 0.");
    }
}
