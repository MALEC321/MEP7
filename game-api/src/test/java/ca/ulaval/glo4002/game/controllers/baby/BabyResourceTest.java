package ca.ulaval.glo4002.game.controllers.baby;

import ca.ulaval.glo4002.game.application.baby.BabyService;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BabyResourceTest extends JerseyTest {
    private BabyService babyService;
    private BabyDtoAssembler babyDtoAssembler;

    @Override
    protected Application configure() {
        babyService = Mockito.mock(BabyService.class);
        babyDtoAssembler = Mockito.mock(BabyDtoAssembler.class);
        return new ResourceConfig().register(new BabyResource(babyService, babyDtoAssembler));
    }

    @Test
    public void givenBabyDinosaurRequest_whenCreateBabyDinosaur_thenReturnedOkStatus() {
        BabyCreationDto babyCreationDto = new BabyCreationDto("name", "fatherName", "motherName");
        when(babyDtoAssembler.fromRequest(anyObject())).thenReturn(babyCreationDto);

        Response response = target("breed").request(MediaType.APPLICATION_JSON_TYPE).post(null);

        verify(babyService).createBaby(babyCreationDto);
        assertEquals(200, response.getStatus());
    }
}