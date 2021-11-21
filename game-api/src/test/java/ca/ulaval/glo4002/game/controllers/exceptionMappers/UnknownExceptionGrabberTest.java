package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnknownExceptionGrabberTest {


    @Test
    public void givenUnknownException_whenExceptionThrown_thenReturnedInternalServerErrorStatus() {
        try {
            throw new Exception();
        } catch (Exception e) {
            UnknownExceptionGrabber exception = new UnknownExceptionGrabber();

            assertEquals(500, exception.toResponse(e).getStatus());
        }
    }
}