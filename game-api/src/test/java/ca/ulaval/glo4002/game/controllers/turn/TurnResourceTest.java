package ca.ulaval.glo4002.game.controllers.turn;

import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TurnResourceTest extends JerseyTest {
    private TurnService turnService;
    private TurnDtoAssembler turnDtoAssembler;

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
        turnService = Mockito.mock(TurnService.class);
        turnDtoAssembler = Mockito.mock(TurnDtoAssembler.class);
        return new ResourceConfig().register(new TurnResource(turnService, turnDtoAssembler));
    }

    @Test
    public void givenMakeATurn_whenTurnIsRequested_thenResponseCodeIsOk() {
        Response response = target("turn").request(MediaType.APPLICATION_JSON_TYPE)
                .post(null);

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void givenMakeATurn_whenTurnIsRequested_thenTurnNumberOneIsExecuted() {
        TurnResponse turnResponse = new TurnResponse(1);
        when(turnDtoAssembler.toResponse(anyObject())).thenReturn(turnResponse);

        Response response = target("turn").request(MediaType.APPLICATION_JSON_TYPE)
                .post(null);

        Assertions.assertEquals(response.readEntity(TurnResponse.class).getTurnNumber(), 1);
        verify(turnService).executeTurn();
    }

    @Test
    void whenReset_thenResponseCodeIsOk() {
        Response response = target("reset").request(MediaType.APPLICATION_JSON_TYPE)
                .post(null);

        verify(turnService).resetGame();
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void whenReset_thenTurnAreReset() {
        target("reset").request(MediaType.APPLICATION_JSON_TYPE)
                .post(null);

        verify(turnService).resetGame();
    }
}