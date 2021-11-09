package ca.ulaval.glo4002.game.application.baby.breed;

import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;

import java.util.Optional;

public interface Breedable {
    Optional<ResponseBreed> createBaby(RequestBreed parentDto);
}
