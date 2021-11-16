package ca.ulaval.glo4002.game.controllers.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange.ChangeWeighDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange.ChangeWeightRequest;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.HttpUrlConnectorProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DinosaurResourceTest extends JerseyTest {
    private static final String KEY_QUERY_PARAM = "name";
    private static final String DINO_NAME = "test";
    private static final int WEIGHT = 10;
    private DinosaurService dinosaurService;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private ChangeWeighDtoAssembler changeWeighDtoAssembler;

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
        dinosaurService = Mockito.mock(DinosaurService.class);
        dinosaurDtoAssembler = Mockito.mock(DinosaurDtoAssembler.class);
        changeWeighDtoAssembler = Mockito.mock(ChangeWeighDtoAssembler.class);
        return new ResourceConfig().register(new DinosaurResource(dinosaurService, dinosaurDtoAssembler, changeWeighDtoAssembler));
    }

    @Override
    protected void configureClient(final ClientConfig config) {
        config.property(HttpUrlConnectorProvider.SET_METHOD_WORKAROUND, true);
    }

    @Test
    void givenCorrectRequest_whenWantToChangeDinoWeight_thenResponseIsOK() {
        ChangeWeightRequest changeWeightRequest = new ChangeWeightRequest(WEIGHT);
        Response response = target("dinosaurs").queryParam(KEY_QUERY_PARAM, DINO_NAME)
                .request()
                .build("PATCH", Entity.entity(changeWeightRequest, MediaType.APPLICATION_JSON)).invoke();

        Assertions.assertEquals(200, response.getStatus());
    }
}