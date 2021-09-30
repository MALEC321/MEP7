package ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities.InvalidSpeciesException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidSpeciesExceptionMapper extends ExceptionResponse implements ExceptionMapper<InvalidSpeciesException> {
    @Override
    public Response toResponse(InvalidSpeciesException e) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
        exceptionResponseDto.error = ExceptionErrorAndDescription.INVALID_SPECIES.getError();
        exceptionResponseDto.description = ExceptionErrorAndDescription.INVALID_SPECIES.getDescription();
        return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
    }
}
