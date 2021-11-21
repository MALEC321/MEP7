package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.InvalidBabyWeightChangeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InvalidBabyWeightChangeExceptionMapperTest {


    @Test
    public void givenArmsTooShortException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidBabyWeightChangeException();
        } catch (InvalidBabyWeightChangeException e) {
            InvalidBabyWeightChangeExceptionMapper exception = new InvalidBabyWeightChangeExceptionMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}