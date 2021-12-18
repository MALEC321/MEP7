package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.AddDino;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.turn.Turn;
import ca.ulaval.glo4002.game.domain.turn.TurnNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DinosaurServiceTest {
    @Spy
    private final Turn turn = new Turn(new TurnNumber(432));
    private final String validDinoName = "Dino";
    private final int weight = 200;
    private final int invalidWeight = -10;
    private final String validGender = "m";
    private final String validSpecie = "Ankylosaurus";
    private final DinosaurCreationDto dinosaurCreationDto = new DinosaurCreationDto(validDinoName, weight, validGender, validSpecie);
    private final DinosaurCreationDto invalidDinosaurCreationDto = new DinosaurCreationDto(validDinoName, invalidWeight, validGender, validSpecie);
    @Mock
    private DinosaurFactory dinosaurFactory;

    @Mock
    private HerdRepository herdRepository;

    @Mock
    private ActionFactory actionFactory;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private Dinosaur validDinosaur;

    @Mock
    private Herd herd;

    @Mock
    private AddDino addDinosaurAction;

    @Mock
    private Game game;

    @InjectMocks
    private DinosaurService dinosaurService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(herdRepository.findCurrent()).thenReturn(herd);
        when(dinosaurFactory.createDinosaur(validDinoName, weight, validGender, validSpecie)).thenReturn(validDinosaur);
        when(dinosaurFactory.createDinosaur(validDinoName, invalidWeight, validGender, validSpecie)).thenThrow(new InvalidWeightException());
        when(actionFactory.createAddDinoAction(validDinosaur, herd)).thenReturn(addDinosaurAction);
        when(gameRepository.findCurrent()).thenReturn(game);
        when(game.currentTurn()).thenReturn(turn);
    }

    @Test
    public void givenValidDinoInformation_whenCreateDinosaur_thenDinosaurIsCreate() {
        dinosaurService.createDinosaur(dinosaurCreationDto);
        verify(dinosaurFactory).createDinosaur(validDinoName, weight, validGender, validSpecie);
    }

    @Test
    public void givenInvalidDinoName_whenCreateDinosaur_thenDinosaurIsNotCreated() {
        assertThrows(InvalidWeightException.class, () -> dinosaurService.createDinosaur(invalidDinosaurCreationDto));
        verify(dinosaurFactory).createDinosaur(validDinoName, invalidWeight, validGender, validSpecie);
        verify(actionFactory, never()).createAddDinoAction(validDinosaur, herd);
        verify(gameRepository, never()).save(game);
    }

    @Test
    public void givenValidDinoInformation_whenCreateDinosaur_thenAddDinoActionIsCreate() {
        dinosaurService.createDinosaur(dinosaurCreationDto);
        verify(actionFactory).createAddDinoAction(validDinosaur, herd);
    }

    @Test
    public void givenValidDinoInformation_whenCreateDinosaur_thenDinosaurIsAddInTurnActions() {
        dinosaurService.createDinosaur(dinosaurCreationDto);

        verify(turn).addAction(addDinosaurAction);
        assertEquals(1, turn.getActions().size());
    }

    @Test
    public void givenValidDinoInformation_whenCreateDinosaur_thenGameStateIsUpdated() {
        dinosaurService.createDinosaur(dinosaurCreationDto);
        verify(gameRepository).save(game);
    }
}