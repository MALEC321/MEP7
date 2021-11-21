package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.MaxCombatsReachedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxCombatsReachedExceptionsMapperTest {


    @Test
    public void givenMaxCombatsReachedException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new MaxCombatsReachedException();
        } catch (MaxCombatsReachedException e) {
            MaxCombatsReachedExceptionsMapper exception = new MaxCombatsReachedExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}