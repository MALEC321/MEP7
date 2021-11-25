package ca.ulaval.glo4002.game.controllers.sumo;

import ca.ulaval.glo4002.game.application.sumo.SumoService;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoDto;
import ca.ulaval.glo4002.game.application.sumo.dtos.SumoResponse;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SumoResourceTest {

    private SumoResource sumoResource;
    SumoService sumoService;

    @BeforeEach
    public void setup () {
        sumoService = mock(SumoService.class);
        sumoResource = new SumoResource(sumoService);
    }

    @Test
    void givenSumoRequest_whenCallingDinosaurFight_thenSendResponse() {
        SumoRequest sumoRequest = new SumoRequest("Maxence", "Beno");
        SumoDto dto = mock(SumoDto.class);
        SumoResponse sumoResponse = new SumoResponse("Maxence");

        when(sumoService.fight(dto)).thenReturn(sumoResponse);
        Response response = sumoResource.dinosaurFight(sumoRequest);
        assertEquals(200, response.getStatus());
    }

}