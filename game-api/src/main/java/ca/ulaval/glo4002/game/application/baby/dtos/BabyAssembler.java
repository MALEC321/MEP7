package ca.ulaval.glo4002.game.application.baby.dtos;

import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;

public class BabyAssembler {
    public RequestBreed toExternalDto(String fatherSpecies, String motherSpecies) {
        return new RequestBreed(fatherSpecies, motherSpecies);
    }
}
