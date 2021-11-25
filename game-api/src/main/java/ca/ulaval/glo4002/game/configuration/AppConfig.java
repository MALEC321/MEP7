package ca.ulaval.glo4002.game.configuration;

import ca.ulaval.glo4002.game.application.baby.BabyRegistrationService;
import ca.ulaval.glo4002.game.application.baby.BabyService;
import ca.ulaval.glo4002.game.application.baby.HealthCenter;
import ca.ulaval.glo4002.game.application.baby.ParentInformationCenter;
import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurService;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.resources.ResourcesFactory;
import ca.ulaval.glo4002.game.application.resources.ResourcesGroupFactory;
import ca.ulaval.glo4002.game.application.resources.ResourcesService;
import ca.ulaval.glo4002.game.application.sumo.SumoService;
import ca.ulaval.glo4002.game.application.turn.TurnService;
import ca.ulaval.glo4002.game.controllers.baby.BabyResource;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.DinosaurResource;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.*;
import ca.ulaval.glo4002.game.controllers.resources.ResourcesResource;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesAssembler;
import ca.ulaval.glo4002.game.controllers.sumo.SumoResource;
import ca.ulaval.glo4002.game.controllers.sumo.dtos.SumoDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.TurnResource;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourcesDistributor;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.infrastructure.client.BabyBreedableClient;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.PantryRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.GameRepositoryInMemory;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;
import java.util.Set;

public class AppConfig {
    private static final TurnFactory turnFactory = new TurnFactory();
    private static final GameRepository gameRepository = new GameRepositoryInMemory();
    private static final PantryRepository resourceRepository = new PantryRepositoryInMemory();
    private static final ActionFactory actionFactory = new ActionFactory();
    private static final SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
    private static final ResourcesDistributor resourcesDistributor = new ResourcesDistributor();

    private static final ResourcesFactory resourcesFactory = new ResourcesFactory();
    private static final ResourcesGroupFactory resourcesGroupFactory = new ResourcesGroupFactory();

    private static final HerdRepository herdRepository = new HerdRepositoryInMemory();
    private static final DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietsCorrespondances);
    private static final TurnService turnService = new TurnService(turnFactory, gameRepository, resourceRepository, herdRepository, resourcesDistributor, resourcesFactory, actionFactory);

    private final ResourceConfig config;

    public AppConfig() {
        this.config = new ResourceConfig();
    }

    private Set<Object> instances() {
        Set<Object> resources = new HashSet<>();
        resources.add(createResourcesResource());
        resources.add(createTurnResource());
        resources.add(createDinosaurResource());
        resources.add(createBabyResource());
        resources.add(createSumoResource());
        resources.add(new InvalidResourceExceptionsMapper());
        resources.add(new UnknownExceptionGrabber());
        resources.add(new NotExistentNameExceptionsMapper());
        resources.add(new InvalidGenderExceptionsMapper());
        resources.add(new InvalidSpeciesExceptionsMapper());
        resources.add(new InvalidWeightExceptionsMapper());
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

    private DinosaurResource createDinosaurResource() {
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        DinosaurService dinosaurService = new DinosaurService(dinosaurFactory, herdRepository, dinosaurAssembler, actionFactory, gameRepository);
        DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
        DinosaurResource dinosaurResource = new DinosaurResource(dinosaurService, dinosaurDtoAssembler);
        return dinosaurResource;
    }

    private ResourcesResource createResourcesResource() {
        ResourcesAssembler resourcesAssembler = new ResourcesAssembler();
        ResourcesService resourcesService = new ResourcesService(resourcesGroupFactory, resourceRepository, resourcesAssembler, actionFactory, gameRepository);
        ResourceDtoAssembler resourceDtoAssembler = new ResourceDtoAssembler();
        ResourcesResource resourcesResource = new ResourcesResource(resourcesService, resourceDtoAssembler);
        return resourcesResource;
    }

    private BabyResource createBabyResource() {
        BabyAssembler babyAssembler = new BabyAssembler();
        Breedable breedable = new BabyBreedableClient();
        ParentInformationCenter parentInformationCenter = new ParentInformationCenter(herdRepository, babyAssembler);
        HealthCenter healthCenter = new HealthCenter(parentInformationCenter, breedable);
        BabyRegistrationService babyRegistrationService = new BabyRegistrationService(herdRepository, actionFactory, dinosaurFactory, gameRepository);
        BabyService babyService = new BabyService(healthCenter, babyRegistrationService);
        BabyDtoAssembler babyDtoAssembler = new BabyDtoAssembler();
        BabyResource createBabyResource = new BabyResource(babyService, babyDtoAssembler);
        return createBabyResource;
    }

    private SumoResource createSumoResource() {
        SumoDtoAssembler sumoDtoAssembler = new SumoDtoAssembler();
        SumoService sumoService = new SumoService(herdRepository, actionFactory, gameRepository);
        SumoResource sumoResource = new SumoResource(sumoService, sumoDtoAssembler);
        return sumoResource;
    }

    private TurnResource createTurnResource() {
        TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
        TurnResource turnResource = new TurnResource(turnService, turnDtoAssembler);
        return turnResource;
    }
}
