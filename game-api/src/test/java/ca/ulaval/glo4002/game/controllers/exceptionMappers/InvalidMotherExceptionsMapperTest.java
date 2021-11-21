package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidMotherException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidMotherExceptionsMapperTest {

    @Test
    public void givenInvalidMotherException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidMotherException();
        } catch (InvalidMotherException e) {
            InvalidMotherExceptionsMapper exception = new InvalidMotherExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}