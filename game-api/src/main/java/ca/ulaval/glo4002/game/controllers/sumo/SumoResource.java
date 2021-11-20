package ca.ulaval.glo4002.game.controllers.sumo;

import ca.ulaval.glo4002.game.application.sumo.SumoService;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoRequest;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sumodino")
public class SumoResource {
    private final SumoService sumoService;

    public SumoResource(SumoService sumoService) {
        this.sumoService = sumoService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDinosaur(SumoRequest sumoRequest) {
        SumoDto dto = new SumoDto(sumoRequest.getChallenger(), sumoRequest.getChallengee());
        SumoResponse response = sumoService.fight(dto);
        return Response.ok().entity(response).build();
    }
}
