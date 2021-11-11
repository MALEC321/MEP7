package ca.ulaval.glo4002.game.controllers.exceptionMappers;

import ca.ulaval.glo4002.game.application.exceptions.GrowInvalidWeightException;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.dtos.ExceptionResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GrowInvalidWeightExceptionMapper implements ExceptionMapper<GrowInvalidWeightException> {
	@Override
	public Response toResponse(GrowInvalidWeightException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "INVALID_WEIGHT";
		exceptionResponse.description = "The specified weight must be equal or greater than 100 kg.";

		return Response.status(Response.Status.BAD_REQUEST).entity(exceptionResponse).type(MediaType.APPLICATION_JSON).build();
	}
}
