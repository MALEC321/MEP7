package ca.ulaval.glo4002.game.application.baby.breed;

import ca.ulaval.glo4002.game.repositories.client.dto.ResponseBreed;
import ca.ulaval.glo4002.game.repositories.client.dto.RequestBreed;

import java.util.Optional;

public interface BabyCreationClient {
    Optional<ResponseBreed> createBaby(RequestBreed parentDto);
}
