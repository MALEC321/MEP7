package ca.ulaval.glo4002.game.exceptions;

public enum ExceptionErrorAndDescription {
    INVALID_RESOURCE_QUANTITY( "INVALID_RESOURCE_QUANTITY","Resource quantities must be positive."),
    DUPLICATE_NAME("DUPLICATE_NAME", "The specified name already exists and must be unique."),
    INVALID_GENDER("INVALID_GENDER", "The specified gender must be \"m\" or \"f\"."),
    INVALID_WEIGHT("INVALID_WEIGHT", "The specified weight must be greater than 0."),
    INVALID_SPECIES("INVALID_SPECIES", "The specified species is not supported."),
    NON_EXISTENT_NAME("NON_EXISTENT_NAME", "The specified name does not exist.");

    private final String error;
    private final String description;

    ExceptionErrorAndDescription(final String error, final String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public String getDescription() {
        return description;
    }
}
