package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurRequest;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.PantryRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.GameRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DinosaurServiceTest {
    private DinosaurService dinosaurService;
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private TurnService turnService;

    @BeforeEach
    void setUp() {
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ResourcesDistributor resourcesDistributor = new ResourcesDistributor();
        ActionFactory actionFactory = new ActionFactory();
        ResourcesFactory resourcesFactory = new ResourcesFactory();

        HerdRepository herdRepository = new HerdRepositoryInMemory();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietsCorrespondances);
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        dinosaurDtoAssembler = new DinosaurDtoAssembler();
        dinosaurService = new DinosaurService(dinosaurFactory, herdRepository, dinosaurAssembler, actionRepository, actionFactory);

        TurnFactory turnFactory = new TurnFactory();
        GameRepository gameRepository = new GameRepositoryInMemory();
        PantryRepository pantryRepository = new PantryRepositoryInMemory();
        turnService = new TurnService(turnFactory, gameRepository, pantryRepository, herdRepository, actionRepository, resourcesDistributor, resourcesFactory);
    }

    @Test
    void whenGetDinosaurNotExistent_shouldThrowsNotExistentNameException() {
        DinosaurRequest dinosaurRequest = new DinosaurRequest("Will", 1000, "m", "Allosaurus");

        DinosaurCreationDto dto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);
        dinosaurService.createDinosaur(dto);
        turnService.executeTurn();

        assertThrows(NotExistentNameException.class, () ->
            dinosaurService.getDinosaur("Willl"));
    }
}