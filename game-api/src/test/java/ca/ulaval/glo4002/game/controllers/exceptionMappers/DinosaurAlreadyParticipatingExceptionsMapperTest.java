package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.DinosaurAlreadyParticipatingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DinosaurAlreadyParticipatingExceptionsMapperTest {

    @Test
    public void givenDinosaurAlreadyParticipatingException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new DinosaurAlreadyParticipatingException();
        } catch (DinosaurAlreadyParticipatingException e) {
            DinosaurAlreadyParticipatingExceptionsMapper exception = new DinosaurAlreadyParticipatingExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}