package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidWeightChangeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidWeightChangeExceptionsMapperTest {


    @Test
    public void givenArmsTooShortException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidWeightChangeException();
        } catch (InvalidWeightChangeException e) {
            InvalidWeightChangeExceptionsMapper exception = new InvalidWeightChangeExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}