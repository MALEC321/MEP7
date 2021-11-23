package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.exceptions.InvalidFatherException;
import ca.ulaval.glo4002.game.application.exceptions.InvalidMotherException;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
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

class ParentInformationCenterTest {

    @Mock
    RequestBreed requestBreed;
    @Mock
    private HerdRepository herdRepository;
    @Mock
    private BabyAssembler babyAssembler;
    @Mock
    Herd herd;
    @InjectMocks
    private ParentInformationCenter parentInformationCenter;

    private final String validFatherName = "Father";
    private final String validFatherSpecie = "Ankylosaurus";
    private final String validFatherGender = "m";
    private final String invalidFatherName = "Mother";
    private final String validMotherName = "";
    private final String validMotherSpecie = "Brachiosaurus";
    private final String validMotherGender = "f";
    private final String invalidMotherName = "";

    @Mock
    private Dinosaur validDinosaurFather;
    @Mock
    private Dinosaur invalidDinosaurFather;
    @Mock
    private Dinosaur validDinosaurMother;
    @Mock
    private Dinosaur invalidDinosaurMother;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(validDinosaurFather.getGender()).thenReturn(validFatherGender);
        when(validDinosaurFather.getSpecies()).thenReturn(validFatherSpecie);
        when(validDinosaurMother.getGender()).thenReturn(validMotherGender);
        when(validDinosaurMother.getSpecies()).thenReturn(validMotherSpecie);
    }

    @Test
    void givenValidInformation_whenGetParentSpecies_thenRequestBreedIsReturn() {
        when(herdRepository.findHerd()).thenReturn(herd);
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
    void givenInvalidFatherName_whenGetParentSpecies_thenNotExistentNameIsThrown() {
        when(herdRepository.findHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(invalidFatherName)).thenReturn(null);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(validDinosaurMother);

        assertThrows(NotExistentNameException.class, () ->
            parentInformationCenter.getParentsSpecies(invalidFatherName, validMotherName));

        verify(herd, times(1)).findDinosaurByName(invalidFatherName);
        verify(herd, never()).findDinosaurByName(validMotherName);
        verify(validDinosaurMother, never()).getGender();
    }

    @Test
    void givenInvalidMotherName_whenGetParentSpecies_thenNotExistentNameIsThrown() {
        when(herdRepository.findHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(validFatherName)).thenReturn(validDinosaurFather);
        when(herd.findDinosaurByName(invalidMotherName)).thenReturn(null);

        assertThrows(NotExistentNameException.class, () ->
            parentInformationCenter.getParentsSpecies(validFatherName, invalidMotherName));

        verify(herd, times(1)).findDinosaurByName(validFatherName);
        verify(herd, times(1)).findDinosaurByName(invalidMotherName);
        verify(validDinosaurFather, never()).getGender();
    }

    @Test
    void givenInvalidFatherGender_whenGetParentSpecies_thenInvalidFatherExceptionIsThrown() {
        when(herdRepository.findHerd()).thenReturn(herd);
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
    void givenInvalidMotherGender_whenGetParentSpecies_thenInvalidMotherExceptionIsThrown() {
        when(herdRepository.findHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(validFatherName)).thenReturn(validDinosaurFather);
        when(herd.findDinosaurByName(validMotherName)).thenReturn(invalidDinosaurMother);
        when(invalidDinosaurMother.getGender()).thenReturn(validFatherGender);

        assertThrows(InvalidMotherException.class, () ->
            parentInformationCenter.getParentsSpecies(validFatherName, validMotherName));

        verify(herd, times(2)).findDinosaurByName(validFatherName);
        verify(herd, times(2)).findDinosaurByName(validMotherName);
    }
}