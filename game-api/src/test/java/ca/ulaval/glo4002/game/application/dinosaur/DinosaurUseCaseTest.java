package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.turn.TurnRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinosaurUseCaseTest {
    private DinosaurUseCase dinosaurUseCase;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private TurnUseCase turnUseCase;

    @BeforeEach
    void setUp() {
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ActionFactory actionFactory = new ActionFactory();

        DinosaurRepository dinosaurRepository = new DinosaurRepositoryInMemory();
        DinosaurFactory  dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        dinosaurDtoAssembler = new DinosaurDtoAssembler();
        dinosaurUseCase = new DinosaurUseCase(dinosaurFactory, dinosaurRepository, dinosaurAssembler, actionRepository, actionFactory);

        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
        TurnAssembler turnAssembler = new TurnAssembler();
        turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, dinosaurRepository, turnAssembler, actionRepository);
    }

    @Test
    void whenGetDinosaurNotExistent_shouldThrowsNotExistentNameException() {
        DinosaurRequest dinosaurRequest = new DinosaurRequest();
        dinosaurRequest.name = "Will";
        dinosaurRequest.weight = 1000;
        dinosaurRequest.gender = "m";
        dinosaurRequest.species = "Allosaurus";

        DinosaurCreationDto dto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);
        dinosaurUseCase.createDinosaur(dto);
        turnUseCase.createTurn();

        assertThrows(NotExistentNameException.class, () ->
                dinosaurUseCase.getDinosaur("Willl"));
    }
}