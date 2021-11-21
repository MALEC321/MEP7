package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidFatherException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidFatherExceptionsMapperTest {


    @Test
    public void givenInvalidFatherException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidFatherException();
        } catch (InvalidFatherException e) {
            InvalidFatherExceptionsMapper exception = new InvalidFatherExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}