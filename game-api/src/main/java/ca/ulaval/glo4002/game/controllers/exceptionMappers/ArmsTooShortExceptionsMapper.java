package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.controllers.exceptionMappers.response.ExceptionResponse;
import ca.ulaval.glo4002.game.domain.actions.ArmsTooShortException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ArmsTooShortExceptionsMapper implements ExceptionMapper<ArmsTooShortException> {
	@Override
	public Response toResponse(ArmsTooShortException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse("ARMS_TOO_SHORT", "Tyrannosaurus Rex can't participate.");
		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
