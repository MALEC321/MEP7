package ca.ulaval.glo4002.game.controllers;

import ca.ulaval.glo4002.game.application.TourService;
import ca.ulaval.glo4002.game.domain.Tour;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TourController {
    private final Tour tour = new Tour();
    private final TourService service = new TourService(tour);

    @Path("/turn")
    @POST
    public TurnResponse myService(){
        return new TurnResponse(service.turn());
    }


    @Path("/reset")
    @POST
    public Response reset(){
        service.reset();
        return Response.ok().build();
    }

}
