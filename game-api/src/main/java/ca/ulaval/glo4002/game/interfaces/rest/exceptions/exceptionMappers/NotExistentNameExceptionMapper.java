package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.NotExistentNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotExistentNameExceptionMapper extends ExceptionResponse implements ExceptionMapper<NotExistentNameException> {
    @Override
    public Response toResponse(NotExistentNameException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.NON_EXISTENT_NAME.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.NON_EXISTENT_NAME.getDescription();
        return exceptionResponse(Response.Status.NOT_FOUND, exceptionResponseDto);
    }
}
