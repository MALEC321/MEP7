package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.GrowInvalidWeightException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GrowInvalidWeightExceptionMapperTest {

    @Test
    public void givenGrowInvalidWeightException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new GrowInvalidWeightException();
        } catch (GrowInvalidWeightException e) {
            GrowInvalidWeightExceptionMapper exception = new GrowInvalidWeightExceptionMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}