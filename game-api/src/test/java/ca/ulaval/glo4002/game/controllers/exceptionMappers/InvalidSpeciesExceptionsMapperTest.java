package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidSpeciesException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidSpeciesExceptionsMapperTest {


    @Test
    public void givenInvalidSpeciesException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidSpeciesException();
        } catch (InvalidSpeciesException e) {
            InvalidSpeciesExceptionsMapper exception = new InvalidSpeciesExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}