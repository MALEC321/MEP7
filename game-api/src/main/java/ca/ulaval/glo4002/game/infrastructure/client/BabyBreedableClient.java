package ca.ulaval.glo4002.game.infrastructure.client;

import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class BabyBreedableClient implements Breedable {

    private static final String URL_CLIENT_EXTERNE = "http://localhost:8080/breed";
    private Client client;

    public BabyBreedableClient() {
        client = ClientBuilder.newClient();
    }

    @Override
    public Optional<ResponseBreed> createBaby(RequestBreed parentDto) {
        ResponseBreed responseBreed = null;
        Response response = client.target(URL_CLIENT_EXTERNE).request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(parentDto, MediaType.APPLICATION_JSON));
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            responseBreed = response.readEntity(ResponseBreed.class);
        }
        return Optional.ofNullable(responseBreed);
    }

}
