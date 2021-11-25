package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.dinosaur.InvalidGenderException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidGenderExceptionsMapperTest {

    @Test
    public void givenInvalidGenderException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new InvalidGenderException();
        } catch (InvalidGenderException e) {
            InvalidGenderExceptionsMapper exception = new InvalidGenderExceptionsMapper();

            assertEquals(400, exception.toResponse(e).getStatus());
        }
    }
}