package ca.ulaval.glo4002.game.controllers.bebe;

import ca.ulaval.glo4002.game.application.bebe.BabyUseCase;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeCreationDto;
import ca.ulaval.glo4002.game.controllers.bebe.assemblers.BebeDtoAssembler;
import ca.ulaval.glo4002.game.application.bebe.dtos.BebeRequest;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.ExternalCreationDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/breed")
public class BabyResource {

    private final BabyUseCase bebeUseCase;
    private final BebeDtoAssembler bebeDtoAssembler;

    public BabyResource(BabyUseCase bebeUseCase, BebeDtoAssembler bebeDtoAssembler) {
        this.bebeUseCase = bebeUseCase;
        this.bebeDtoAssembler = bebeDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBebe(BebeRequest bebeRequest) {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/breed").request(MediaType.APPLICATION_JSON).post(Entity.entity(bebeRequest, MediaType.APPLICATION_JSON));
//        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
//        Response response = invocationBuilder.post(Entity.entity(bebeRequest, MediaType.APPLICATION_JSON));
        System.out.println(response);

        BebeCreationDto dto = bebeDtoAssembler.fromRequest(bebeRequest);
        bebeUseCase.createBebe(dto);

        return Response.status(Response.Status.OK).build();
    }
}
