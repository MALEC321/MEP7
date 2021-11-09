package ca.ulaval.glo4002.game.application.configuration;

import ca.ulaval.glo4002.game.application.baby.BabyUseCase;
import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurUseCase;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.application.resources.ResourceUseCase;
import ca.ulaval.glo4002.game.application.resources.dtos.ResourceAssemblers;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.baby.BabyResource;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.DinosaurResource;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.exceptionMappers.*;
import ca.ulaval.glo4002.game.controllers.resources.ResourceResource;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.TurnResource;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import ca.ulaval.glo4002.game.infrastructure.client.BabyBreedableClient;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.turn.TurnRepositoryInMemory;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.HashSet;
import java.util.Set;

public class AppConfig {
    // Turn
    private static final TurnFactory turnFactory = new TurnFactory();
    private static final TurnRepository turnRepository = new TurnRepositoryInMemory();
    private static final ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
    private static final ActionRepository actionRepository = new ActionRepositoryInMemory();
    private static final ActionFactory actionFactory = new ActionFactory();
    private static final SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
    private static final ZooManager zooManager = new ZooManager();

    // Dinosaur
    private static final HerdRepository herdRepository = new HerdRepositoryInMemory();
    private static final DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepository, speciesDietsCorrespondances);
    private static final DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
    private static final DinosaurUseCase dinosaurUseCase = new DinosaurUseCase(dinosaurFactory, herdRepository, dinosaurAssembler, actionRepository, actionFactory);

    private static final DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
    private static final DinosaurResource manageDinosaurResource = new DinosaurResource(dinosaurUseCase, dinosaurDtoAssembler);

    private static final TurnUseCase turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, herdRepository, actionRepository, zooManager);

    private static final TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
    private static final TurnResource executeTurnResource = new TurnResource(turnUseCase, turnDtoAssembler);

    private static final ResourceFactory resourceFactory = new ResourceFactory();
    private static final ResourceAssemblers resourceAssemblers = new ResourceAssemblers();
    private static final ResourceUseCase resourceUseCase = new ResourceUseCase(resourceFactory, resourceRepository, resourceAssemblers, actionRepository, actionFactory);
    private static final ResourceDtoAssembler resourceDtoAssembler = new ResourceDtoAssembler();
    private static final ResourceResource manageResources = new ResourceResource(resourceUseCase, resourceDtoAssembler);

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
        resources.add(new InvalidWeightExceptionsMapper());
        resources.add(new DuplicateNameExceptionsMapper());
        resources.add(new InvalidMotherExceptionsMapper());
        resources.add(new InvalidFatherExceptionsMapper());
        return resources;
    }

    public ResourceConfig getConfig() {
        return this.config.registerInstances(instances());
    }
}
