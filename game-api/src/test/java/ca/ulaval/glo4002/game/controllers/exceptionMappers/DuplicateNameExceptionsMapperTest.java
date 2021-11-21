package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.DuplicateNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplicateNameExceptionsMapperTest {

    @Test
    public void givenDuplicateNameException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new DuplicateNameException();
        } catch (DuplicateNameException e) {
            DuplicateNameExceptionsMapper exception = new DuplicateNameExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}