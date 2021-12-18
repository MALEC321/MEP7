package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.NotExistentNameException;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.infrastructure.client.dto.RequestBreed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ParentInformationCenterTest {
    private final String validFatherName = "Father";
    private final String validFatherSpecie = "Ankylosaurus";
    private final String validFatherGender = "m";
    private final String invalidFatherName = "Mother";
    private final String validMotherName = "";
    private final String validMotherSpecie = "Brachiosaurus";
    private final String validMotherGender = "f";
    private final String invalidMotherName = "";
    @Mock
    private RequestBreed requestBreed;

    @Mock
    private Herd herd;

    @Mock
    private HerdRepository herdRepository;

    @Mock
    private BabyAssembler babyAssembler;

    @InjectMocks
    private ParentInformationCenter parentInformationCenter;

    @Mock
    private Dinosaur validDinosaurFather;

    @Mock
    private Dinosaur invalidDinosaurFather;

    @Mock
    private Dinosaur validDinosaurMother;

    @Mock
    private Dinosaur invalidDinosaurMother;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(herdRepository.findCurrent()).thenReturn(herd);
        when(validDinosaurFather.getGender()).thenReturn(validFatherGender);
        when(validDinosaurFather.getSpecies()).thenReturn(validFatherSpecie);
        when(validDinosaurMother.getGender()).thenReturn(validMotherGender);
        when(validDinosaurMother.getSpecies()).thenReturn(validMotherSpecie);
    }

    @Test
    public void givenValidInformation_whenGetParentSpecies_thenRequestBreedIsReturn() {
        when(herd.findDinosaurByName(validFatherName)).thenReturn(validDinosaurFather);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(validDinosaurMother);
        when(babyAssembler.toExternalDto(validFatherSpecie, validMotherSpecie)).thenReturn(requestBreed);

        assertEquals(requestBreed, parentInformationCenter.getParentsSpecies(validFatherName, validMotherName));

        verify(herd, times(3)).findDinosaurByName(validFatherName);
        verify(herd, times(3)).findDinosaurByName(validMotherName);
        verify(validDinosaurFather).getGender();
        verify(validDinosaurMother).getGender();
    }

    @Test
    public void givenInvalidFatherName_whenGetParentSpecies_thenNotExistentNameIsThrown() {
        when(herd.findDinosaurByName(invalidFatherName)).thenReturn(null);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(validDinosaurMother);

        assertThrows(NotExistentNameException.class, () ->
            parentInformationCenter.getParentsSpecies(invalidFatherName, validMotherName));

        verify(herd, times(1)).findDinosaurByName(invalidFatherName);
        verify(herd, never()).findDinosaurByName(validMotherName);
        verify(validDinosaurMother, never()).getGender();
    }

    @Test
    public void givenInvalidMotherName_whenGetParentSpecies_thenNotExistentNameIsThrown() {
        when(herd.findDinosaurByName(validFatherName)).thenReturn(validDinosaurFather);
        when(herd.findDinosaurByName(invalidMotherName)).thenReturn(null);

        assertThrows(NotExistentNameException.class, () ->
            parentInformationCenter.getParentsSpecies(validFatherName, invalidMotherName));

        verify(herd, times(1)).findDinosaurByName(validFatherName);
        verify(herd, times(1)).findDinosaurByName(invalidMotherName);
        verify(validDinosaurFather, never()).getGender();
    }

    @Test
    public void givenInvalidFatherGender_whenGetParentSpecies_thenInvalidFatherExceptionIsThrown() {
        when(herd.findDinosaurByName(validFatherName)).thenReturn(invalidDinosaurFather);
        when(invalidDinosaurFather.getGender()).thenReturn(validMotherGender);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(validDinosaurMother);

        assertThrows(InvalidFatherException.class, () ->
            parentInformationCenter.getParentsSpecies(validFatherName, validMotherName));

        verify(herd, times(2)).findDinosaurByName(validFatherName);
        verify(herd, times(1)).findDinosaurByName(validMotherName);
        verify(validDinosaurMother, never()).getGender();
    }

    @Test
    public void givenInvalidMotherGender_whenGetParentSpecies_thenInvalidMotherExceptionIsThrown() {
        when(herd.findDinosaurByName(validFatherName)).thenReturn(validDinosaurFather);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(invalidDinosaurMother);
        when(invalidDinosaurMother.getGender()).thenReturn(validFatherGender);

        assertThrows(InvalidMotherException.class, () ->
            parentInformationCenter.getParentsSpecies(validFatherName, validMotherName));

        verify(herd, times(2)).findDinosaurByName(validFatherName);
        verify(herd, times(2)).findDinosaurByName(validMotherName);
    }
}