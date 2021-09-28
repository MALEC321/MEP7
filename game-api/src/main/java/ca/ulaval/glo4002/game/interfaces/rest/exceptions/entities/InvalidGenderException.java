package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class InvalidGenderException extends HTTPExceptionItem {
    public InvalidGenderException() {
        super(400, "INVALID_GENDER", "The specified gender must be \"m\" or \"f\".");
    }
}
