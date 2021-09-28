package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

public class InvalidSpeciesException extends HTTPExceptionItem {
    public InvalidSpeciesException() {
        super(400, "INVALID_SPECIES", "The specified species is not supported.");
    }
}
