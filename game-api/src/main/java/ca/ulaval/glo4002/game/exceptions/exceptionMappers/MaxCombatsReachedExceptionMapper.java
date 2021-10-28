package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionErrorAndDescription;
import ca.ulaval.glo4002.game.exceptions.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponseDto;
import ca.ulaval.glo4002.game.exceptions.types.MaxCombatsReachedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MaxCombatsReachedExceptionMapper extends ExceptionResponse implements ExceptionMapper<MaxCombatsReachedException> {
	@Override
	public Response toResponse(MaxCombatsReachedException e) {
		ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto();
		exceptionResponseDto.error = ExceptionErrorAndDescription.MAX_COMBATS_REACHED.getError();
		exceptionResponseDto.description = ExceptionErrorAndDescription.MAX_COMBATS_REACHED.getDescription();
		return exceptionResponse(Response.Status.BAD_REQUEST, exceptionResponseDto);
	}
}
