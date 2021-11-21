package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotExistentNameExceptionsMapperTest {


    @Test
    public void givenNotExistentNameException_whenExceptionThrown_thenReturnedBadRequestStatus() {
        try {
            throw new NotExistentNameException();
        } catch (NotExistentNameException e) {
            NotExistentNameExceptionsMapper exception = new NotExistentNameExceptionsMapper();

            assertEquals(404, exception.toResponse(e).getStatus());
        }
    }
}