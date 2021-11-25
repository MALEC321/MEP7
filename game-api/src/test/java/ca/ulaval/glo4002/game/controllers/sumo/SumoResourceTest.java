package ca.ulaval.glo4002.game.controllers.sumo;

import ca.ulaval.glo4002.game.application.sumo.SumoService;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoDtoAssembler;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SumoResourceTest extends JerseyTest {
    private SumoRequest sumoRequest;
    private SumoService sumoService;
    private SumoDtoAssembler sumoDtoAssembler;

    @BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Override
    protected Application configure() {
        sumoRequest = mock(SumoRequest.class);
        sumoService = mock(SumoService.class);
        sumoDtoAssembler = mock(SumoDtoAssembler.class);
        return new ResourceConfig().register(new SumoResource(sumoService, sumoDtoAssembler));
    }

    @Test
    public void givenSumoRequest_whenCallingDinosaurFight_thenResponseCodeIsOk() {
        setSumoFightContext();

        Response response = target("sumodino").request(MediaType.APPLICATION_JSON_TYPE).post(null);

        verify(sumoService).fight(any());
        assertEquals(200, response.getStatus());
    }

    private void setSumoFightContext() {
        SumoResponse sumoResponse = new SumoResponse("Beno");
        SumoDto sumoDto = new SumoDto("Maxence", "Beno");
        when(sumoDtoAssembler.fromRequest(sumoRequest)).thenReturn(sumoDto);
        when(sumoService.fight(sumoDto)).thenReturn(sumoResponse);
    }
}