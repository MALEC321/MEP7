package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class DinosaurFactoryTest {
    private static final Dinosaur NO_DINO_WITH_SAME_NAME = null;
    private static final String MOTHER_NAME = "mother";
    private static final String FATHER_NAME = "father";
    private static final String BABYDINO_NAME = "Carey Price";
    private static final String MALE = "m";
    private static final String PARENT_SPECIES = "Megalosaurus";
    private static final String FEMALE = "f";
    private static final int PARENT_WEIGHT = 50000;
    private static final String BABY_SPECIES = "Ankylosaurus";
    private static final String INVALID_SPECIES = "Raptors";
    private static final String INVALID_GENDER = "x";
    private static final String REGULAR_NAME = "George";
    private static final int REGULAR_WEIGHT = 100;
    private static final int NEGATIVE_WEIGHT = -3;

    private Dinosaur mother;
    private Dinosaur father ;
    private Dinosaur dinosaurBaby;

    @InjectMocks
    private DinosaurFactory dinosaurFactory;

    @Mock
    private HerdRepository herdRepository;

    @Mock
    private DietStrategyFactory dietStrategyFactory;

    @Mock
    private Herd herd;

    @Mock
    private SpeciesDietsCorrespondances speciesDietsCorrespondances;

    @BeforeEach
    public void setUp() {MockitoAnnotations.initMocks(this);}

    @Test
    public void givenADinosaur_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        setDuplicatedNameContext();
        assertThrows(DuplicateNameException.class, () ->
                dinosaurFactory.createDinosaur(REGULAR_NAME, REGULAR_WEIGHT, FEMALE, BABY_SPECIES));
    }

    @Test
    public void givenADinosaur_whenGenderIsInvalid_thenThrowsInvalidGenderException() {
        setNoDuplicatedNameContext();
        assertThrows(InvalidGenderException.class, () ->
                dinosaurFactory.createDinosaur(REGULAR_NAME, REGULAR_WEIGHT, INVALID_GENDER, BABY_SPECIES));
    }

    @Test
    public void givenADinosaur_whenWeightIsNegative_thenThrowsInvalidWeightException() {
        setNoDuplicatedNameContext();
        assertThrows(InvalidWeightException.class, () ->
                dinosaurFactory.createDinosaur(REGULAR_NAME, NEGATIVE_WEIGHT, FEMALE, BABY_SPECIES));
    }

    @Test
    public void givenADinosaur_whenSpecieIsNotSupported_thenThrowsInvalidSpeciesException() {
        setNoDuplicatedNameContext();
        assertThrows(InvalidSpeciesException.class, () ->
                dinosaurFactory.createDinosaur(REGULAR_NAME, REGULAR_WEIGHT, FEMALE, INVALID_SPECIES));
    }

    @Test
    public void givenADinosaurBaby_whenNameIsAlreadyTaken_thenThrowsDuplicateNameException() {
        setBabyDinosParents();
        when(herdRepository.findCurrentHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(father.getName())).thenReturn(father);

        assertThrows(DuplicateNameException.class, () ->
                dinosaurFactory.createBabyDinosaur(FATHER_NAME, father, mother, MALE, BABY_SPECIES));
    }

    @Test
    public void givenABabyDinosaur_whenCreating_thenWeightIsOne() {
        setBabyDinosParents();
        createBabyDino();
        assertEquals(1, dinosaurBaby.getWeight());
    }

    @Test
    public void givenBabyDinosInfo_whenCreating_thenBabyIsCreated() {
        setBabyDinoDuplicatedNameValidation();
        setBabyDinosParents();
        createBabyDino();

        Dinosaur currentDino = dinosaurFactory.createBabyDinosaur(BABYDINO_NAME, father, mother, MALE, BABY_SPECIES);

        assertEquals(dinosaurBaby, currentDino);
    }

    @Test
    public void givenBabyDinosInfo_whenCreating_thenBabyHaveParents() {
        setBabyDinoDuplicatedNameValidation();
        setBabyDinosParents();

        dinosaurBaby = dinosaurFactory.createBabyDinosaur(BABYDINO_NAME, father, mother, MALE, BABY_SPECIES);

        assertFalse(dinosaurBaby.isOrphan());
    }

    private void setBabyDinoDuplicatedNameValidation() {
        when(herdRepository.findCurrentHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(BABYDINO_NAME)).thenReturn(NO_DINO_WITH_SAME_NAME);
    }

    private void setBabyDinosParents() {
        mother = new Dinosaur(MOTHER_NAME, PARENT_WEIGHT, FEMALE, dietStrategyFactory.create(PARENT_SPECIES), PARENT_SPECIES);
        father = new Dinosaur(FATHER_NAME, PARENT_WEIGHT, MALE,dietStrategyFactory.create(PARENT_SPECIES), PARENT_SPECIES);
    }

    private void setDuplicatedNameContext() {
        when(herdRepository.findCurrentHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(anyString())).thenReturn(new Dinosaur());
    }

    private void setNoDuplicatedNameContext() {
        when(herdRepository.findCurrentHerd()).thenReturn(herd);
        when(herd.findDinosaurByName(anyString())).thenReturn(NO_DINO_WITH_SAME_NAME);
    }

    private void createBabyDino() {
        dinosaurBaby = new Dinosaur(BABYDINO_NAME, DinosaurFactory.DEFAULT_BABY_WEIGHT, MALE, dietStrategyFactory.create(PARENT_SPECIES),
                 mother, father, BABY_SPECIES);
    }
}