package ca.ulaval.glo4002.game.configuration;

import ca.ulaval.glo4002.game.application.baby.BabyUseCase;
import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.application.resources.ResourcesGroupFactory;
import ca.ulaval.glo4002.game.application.resources.ResourcesService;
import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.baby.BabyResource;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.DinosaurResource;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.*;
import ca.ulaval.glo4002.game.controllers.resources.ResourcesResource;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesAssembler;
import ca.ulaval.glo4002.game.controllers.turn.TurnResource;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.infrastructure.client.BabyBreedableClient;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.PantryRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.GameRepositoryInMemory;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;
import java.util.Set;

public class AppConfig {
    // Turn
    private static final TurnFactory turnFactory = new TurnFactory();
    private static final GameRepository gameRepository = new GameRepositoryInMemory();
    private static final PantryRepository resourceRepository = new PantryRepositoryInMemory();
    private static final ActionRepository actionRepository = new ActionRepositoryInMemory();
    private static final ActionFactory actionFactory = new ActionFactory();
    private static final SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
    private static final ResourcesDistributor resourcesDistributor = new ResourcesDistributor();

    private static final ResourcesFactory resourcesFactory = new ResourcesFactory();
    private static final ResourcesGroupFactory resourcesGroupFactory = new ResourcesGroupFactory();


    // Dinosaur
    private static final HerdRepository herdRepository = new HerdRepositoryInMemory();
    private static final DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietsCorrespondances);
    private static final DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
    private static final DinosaurService dinosaurService = new DinosaurService(dinosaurFactory, herdRepository, dinosaurAssembler, actionRepository, actionFactory);

    private static final DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
    private static final DinosaurResource manageDinosaurResource = new DinosaurResource(dinosaurService, dinosaurDtoAssembler);

    private static final TurnService turnService = new TurnService(turnFactory, gameRepository, resourceRepository, herdRepository, actionRepository, resourcesDistributor, resourcesFactory);

    private static final TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
    private static final TurnResource executeTurnResource = new TurnResource(turnService, turnDtoAssembler);

    private static final ResourcesAssembler resourcesAssembler = new ResourcesAssembler();
    private static final ResourcesService
            resourcesService = new ResourcesService(resourcesGroupFactory, resourceRepository, resourcesAssembler, actionRepository, actionFactory);
    private static final ResourceDtoAssembler resourceDtoAssembler = new ResourceDtoAssembler();
    private static final ResourcesResource manageResources = new ResourcesResource(resourcesService, resourceDtoAssembler);

    // Baby
    private static final BabyAssembler babyAssembler = new BabyAssembler();
    private static final Breedable breedable = new BabyBreedableClient();
    private static final BabyUseCase babyUseCase = new BabyUseCase(herdRepository, babyAssembler, actionRepository, actionFactory, dinosaurFactory, breedable);

    private static final BabyDtoAssembler babyDtoAssembler = new BabyDtoAssembler();
    private static final BabyResource createBabyResource = new BabyResource(babyUseCase, babyDtoAssembler);
    private final ResourceConfig config;

    public AppConfig() {
        this.config = new ResourceConfig();
    }

    private Set<Object> instances() {
        Set<Object> resources = new HashSet<>();
        resources.add(manageResources);
        resources.add(executeTurnResource);
        resources.add(manageDinosaurResource);
        resources.add(createBabyResource);
        resources.add(new InvalidResourceExceptionsMapper());
        resources.add(new UnknownExceptionGrabber());
        resources.add(new NotExistentNameExceptionsMapper());
        resources.add(new InvalidGenderExceptionsMapper());
        resources.add(new InvalidSpeciesExceptionsMapper());
        resources.add(new DuplicateNameExceptionsMapper());
        resources.add(new InvalidMotherExceptionsMapper());
        resources.add(new InvalidFatherExceptionsMapper());
        resources.add(new ArmsTooShortExceptionsMapper());
        resources.add(new DinosaurAlreadyParticipatingExceptionsMapper());
        resources.add(new MaxCombatsReachedExceptionsMapper());
        return resources;
    }

    public ResourceConfig getConfig() {
        return this.config.registerInstances(instances());
    }
}
