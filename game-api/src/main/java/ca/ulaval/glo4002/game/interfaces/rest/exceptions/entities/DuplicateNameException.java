package ca.ulaval.glo4002.game.interfaces.rest.exceptions.entities;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class DuplicateNameException extends HTTPExceptionItem {
    public DuplicateNameException() {
        super(404, "DUPLICATE_NAME", "The specified name already exists and must be unique.");
    }
}
