package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidWeightException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidWeightExceptionsMapperTest {


    @Test
    public void givenInvalidWeightException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidWeightException();
        } catch (InvalidWeightException e) {
            InvalidWeightExceptionsMapper exception = new InvalidWeightExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}