package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidResourceQuantityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidResourceExceptionsMapperTest {


    @Test
    public void givenInvalidResourceQuantityException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidResourceQuantityException();
        } catch (InvalidResourceQuantityException e) {
            InvalidResourceExceptionsMapper exception = new InvalidResourceExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}