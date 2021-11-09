package ca.ulaval.glo4002.game.application.baby.dtos;

import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;

public class BabyAssembler {

    public BabyDto toDto(DinosaurBaby baby) {
        return new BabyDto(baby.getName(), baby.getFatherName(), baby.getMotherName());
    }

    public RequestBreed toExternalDto(String fatherSpecies, String motherSpecies) {
        return new RequestBreed(fatherSpecies, motherSpecies);
    }
}
