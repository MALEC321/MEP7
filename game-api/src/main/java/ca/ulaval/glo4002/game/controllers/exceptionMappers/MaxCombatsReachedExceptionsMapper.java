package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.actions.MaxCombatsReachedException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MaxCombatsReachedExceptionsMapper implements ExceptionMapper<MaxCombatsReachedException> {
	@Override
	public Response toResponse(MaxCombatsReachedException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("MAX_COMBATS_REACHED","Max number of combats has been reached.");
		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
