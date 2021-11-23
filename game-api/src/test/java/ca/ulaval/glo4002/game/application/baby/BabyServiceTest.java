package ca.ulaval.glo4002.game.application.baby;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

class BabyServiceTest {
    @Mock
    private BabyCreationDto babyCreationDto;

    @Mock
    private HealthCenter healthCenter;

    @Mock
    private BabyRegistrationService babyRegistrationService;

    @Mock
    private ResponseBreed babyDto;

    @InjectMocks
    private BabyService babyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenBabyInformation_whenBabyIsBorn_thenBabyInformationAreSaved() {
        when(healthCenter.tryToGiveBirthToBaby(babyCreationDto)).thenReturn(Optional.of(babyDto));

        babyService.createBaby(babyCreationDto);

        verify(healthCenter).tryToGiveBirthToBaby(babyCreationDto);
        verify(babyRegistrationService).saveBabyInformationIfBabyAlived(babyCreationDto, Optional.of(babyDto));
    }

    @Test
    public void givenWrongBabyInformation_whenTryingToBirthBaby_thenNotExistentNameExceptionIsThrown() {
        when(healthCenter.tryToGiveBirthToBaby(babyCreationDto)).thenThrow(new NotExistentNameException());

        assertThrows(NotExistentNameException.class, () ->
            babyService.createBaby(babyCreationDto));

        verify(healthCenter).tryToGiveBirthToBaby(babyCreationDto);
        verify(babyRegistrationService, never()).saveBabyInformationIfBabyAlived(babyCreationDto, Optional.of(babyDto));
    }

}