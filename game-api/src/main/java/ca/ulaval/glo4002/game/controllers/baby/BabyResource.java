package ca.ulaval.glo4002.game.controllers.baby;

import ca.ulaval.glo4002.game.application.baby.BabyUseCase;
import ca.ulaval.glo4002.game.application.baby.dtos.ExternalApiBebeListDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyRequest;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.ExternalApiCreationDto;

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
    private final BabyDtoAssembler babyDtoAssembler;

    public BabyResource(BabyUseCase bebeUseCase, BabyDtoAssembler babyDtoAssembler) {
        this.bebeUseCase = bebeUseCase;
        this.babyDtoAssembler = babyDtoAssembler;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBebe(BabyRequest babyRequest) {
        //Get Bebe dinosaur's parents
        ExternalApiCreationDto externalApiCreationDto;
        externalApiCreationDto = bebeUseCase.getParentsSpecies(babyRequest.fatherName, babyRequest.motherName);

        //Call to external API
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/breed").request(MediaType.APPLICATION_JSON).post(Entity.entity(externalApiCreationDto, MediaType.APPLICATION_JSON));
        ExternalApiBebeListDto externalApiBebeListDto = response.readEntity(ExternalApiBebeListDto.class);
        //Create Baby dinosaur
        BabyCreationDto dto = babyDtoAssembler.fromRequest(babyRequest, externalApiBebeListDto);
        bebeUseCase.createBebe(dto);

        return Response.status(Response.Status.OK).build();
    }
}
