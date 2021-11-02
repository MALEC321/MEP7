package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.MaxCombatsReachedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MaxCombatsReachedExceptionMapper extends ca.ulaval.glo4002.game.exceptions.ExceptionMapper implements ExceptionMapper<MaxCombatsReachedException> {
	@Override
	public Response toResponse(MaxCombatsReachedException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "MAX_COMBATS_REACHED";
		exceptionResponse.description = "Max number of combats has been reached.";
		return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
	}
}
