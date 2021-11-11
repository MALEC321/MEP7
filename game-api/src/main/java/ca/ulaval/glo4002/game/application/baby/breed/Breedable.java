package ca.ulaval.glo4002.game.application.baby.breed;

import java.util.Optional;

import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

public interface Breedable {
    Optional<ResponseBreed> createBaby(RequestBreed parentDto);
}
