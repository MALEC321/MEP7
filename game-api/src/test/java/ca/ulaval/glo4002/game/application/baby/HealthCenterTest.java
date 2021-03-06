package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.dinosaur.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HealthCenterTest {
    private final String fatherName = "father";
    private final String motherName = "father";
    @Mock
    private BabyCreationDto babyCreationDto;

    @Mock
    private RequestBreed requestBreed;

    @Mock
    private ResponseBreed responseBreed;

    @Mock
    private Breedable breedable;

    @Mock
    private ParentInformationCenter parentInformationCenter;

    @InjectMocks
    private HealthCenter healthCenter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(babyCreationDto.getFatherName()).thenReturn(fatherName);
        when(babyCreationDto.getMotherName()).thenReturn(motherName);
    }

    @Test
    public void givenBabyInformation_whenGetBabyBreed_thenResponseBreedIsReturn() {
        when(parentInformationCenter.getParentsSpecies(fatherName, motherName)).thenReturn(requestBreed);
        when(breedable.createBaby(requestBreed)).thenReturn(Optional.of(responseBreed));

        assertEquals(Optional.of(responseBreed), healthCenter.tryToGiveBirthToBaby(babyCreationDto));
        verify(parentInformationCenter).getParentsSpecies(fatherName, motherName);
    }

    @Test
    public void givenBabyDinosaurInformation_whenParentInformationAreWrong_thenExceptionIsThrown() {
        when(parentInformationCenter.getParentsSpecies(fatherName, motherName)).thenThrow(new NotExistentNameException());

        assertThrows(NotExistentNameException.class, () ->
            healthCenter.tryToGiveBirthToBaby(babyCreationDto));
        verify(parentInformationCenter).getParentsSpecies(fatherName, motherName);
        verify(breedable, never()).createBaby(requestBreed);
    }
}