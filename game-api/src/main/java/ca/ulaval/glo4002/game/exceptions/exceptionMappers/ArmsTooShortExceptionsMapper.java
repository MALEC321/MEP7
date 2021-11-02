package ca.ulaval.glo4002.game.exceptions.exceptionMappers;

import ca.ulaval.glo4002.game.exceptions.ExceptionsMapper;
import ca.ulaval.glo4002.game.exceptions.dtos.ExceptionResponse;
import ca.ulaval.glo4002.game.exceptions.types.ArmsTooShortException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ArmsTooShortExceptionsMapper extends ExceptionsMapper implements ExceptionMapper<ArmsTooShortException> {
	@Override
	public Response toResponse(ArmsTooShortException e) {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.error = "ARMS_TOO_SHORT";
		exceptionResponse.description = "Tyrannosaurus Rex can't participate.";
		return exceptionMapper(Response.Status.BAD_REQUEST, exceptionResponse);
	}
}
