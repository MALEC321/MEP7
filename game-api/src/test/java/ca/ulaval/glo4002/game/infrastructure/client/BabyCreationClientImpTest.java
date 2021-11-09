package ca.ulaval.glo4002.game.infrastructure.client;

import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;

class BabyCreationClientImpTest {

    private static final String FATHER_SPECIE = "fatherSpecie";
    private static final String MOTHER_SPECIE = "motherSpecie";
    @InjectMocks
    BabyBreedableClient babyCreationClient;

    @Mock
    Client clientMock;

    @Mock
    WebTarget webTargetMock;

    @Mock
    Response responseMock;

    @Mock
    Invocation.Builder builderMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        setClientBreed();
    }

    @Test
    void whenCallExternalApiResponse200_thenBabyDinoCreated() {
        Mockito.when(responseMock.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
        RequestBreed requestBreed = new RequestBreed(FATHER_SPECIE, MOTHER_SPECIE);

        Optional<ResponseBreed> response = babyCreationClient.createBaby(requestBreed);

        assertTrue(response.isPresent());
        assertTrue(response.get().getGender().equalsIgnoreCase("m"));
        assertTrue(response.get().getOffspring().equalsIgnoreCase("dino"));
    }

    @Test
    void whenCallExternalApiResponse400_thenBabyDinoNotCreated() {
        Mockito.when(responseMock.getStatus()).thenReturn(Response.Status.BAD_REQUEST.getStatusCode());
        RequestBreed requestBreed = new RequestBreed(FATHER_SPECIE, FATHER_SPECIE);

        Optional<ResponseBreed> response = babyCreationClient.createBaby(requestBreed);

        assertFalse(response.isPresent());
    }

    private void setClientBreed() {
        ResponseBreed responseBreed = new ResponseBreed("dino", "m");
        Mockito.when(clientMock.target(anyString())).thenReturn(webTargetMock);
        Mockito.when(webTargetMock.request(anyString())).thenReturn(builderMock);
        Mockito.when(builderMock.post(anyObject())).thenReturn(responseMock);
        Mockito.when(responseMock.readEntity(ResponseBreed.class)).thenReturn(responseBreed);
    }
}