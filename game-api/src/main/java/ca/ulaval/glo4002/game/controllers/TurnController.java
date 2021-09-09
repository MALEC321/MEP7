package ca.ulaval.glo4002.game.controllers;


import ca.ulaval.glo4002.game.application.TurnService;
import ca.ulaval.glo4002.game.domain.Turn;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TurnController {
    private final Turn turn = new Turn();
    private final TurnService service = new TurnService(turn);

    @Path("/turn")
    @POST
    public TurnResponse myService() {
        return new TurnResponse(service.turn());
    }


    @Path("/reset")
    @POST
    public Response reset() {
        service.reset();
        return Response.ok().build();
    }
}
