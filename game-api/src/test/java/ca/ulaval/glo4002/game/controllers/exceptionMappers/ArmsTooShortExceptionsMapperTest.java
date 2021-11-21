package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.ArmsTooShortException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmsTooShortExceptionsMapperTest {

    @Test
    public void givenArmsTooShortException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new ArmsTooShortException();
        } catch (ArmsTooShortException e) {
            ArmsTooShortExceptionsMapper exception = new ArmsTooShortExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}