package ca.ulaval.glo4002.game.application.baby;

import java.util.Optional;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

public class BabyService {
    private final HealthCenter healthCenter;
    private final BabyRegistrationService babyRegistrationService;

    public BabyService(HealthCenter healthCenter, BabyRegistrationService babyRegistrationService) {
        this.healthCenter = healthCenter;
        this.babyRegistrationService = babyRegistrationService;
    }

    public void createBaby(BabyCreationDto dto) {
        Optional<ResponseBreed> babyDto = healthCenter.tryToGiveBirthToBaby(dto);
        babyRegistrationService.saveBabyInformationIfBabyAlived(dto, babyDto);
    }
}
