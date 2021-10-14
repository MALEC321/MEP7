package ca.ulaval.glo4002.game.configuration;

import ca.ulaval.glo4002.game.application.baby.BabyUseCase;
import ca.ulaval.glo4002.game.application.manager.ZooManager;
import ca.ulaval.glo4002.game.controllers.baby.BabyResource;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyDtoAssembler;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.application.dinosaur.DinosaurUseCase;
import ca.ulaval.glo4002.game.repositories.resources.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.application.resources.ResourceUseCase;
import ca.ulaval.glo4002.game.repositories.turn.TurnRepositoryInMemory;
import ca.ulaval.glo4002.game.application.turn.TurnUseCase;
import ca.ulaval.glo4002.game.controllers.dinosaur.DinosaurResource;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.controllers.resources.ResourceResource;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceAssemblers;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.controllers.turn.TurnResource;
import ca.ulaval.glo4002.game.controllers.turn.dtos.TurnDtoAssembler;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.domain.resources.ResourceFactory;
import ca.ulaval.glo4002.game.domain.turn.TurnFactory;
import ca.ulaval.glo4002.game.exceptions.exceptionMappers.*;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;
import ca.ulaval.glo4002.game.domain.turn.TurnRepository;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig {
    // Turn
    static TurnFactory turnFactory = new TurnFactory();
    static TurnRepository turnRepository = new TurnRepositoryInMemory();
    static ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
    static ActionRepository actionRepository = new ActionRepositoryInMemory();
    static ActionFactory actionFactory = new ActionFactory();
    static SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
    static ZooManager zooManager = new ZooManager();


    // Dinosaur
    static DinosaurRepository dinosaurRepository = new DinosaurRepositoryInMemory();
    static DinosaurFactory dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);
    static DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
    static DinosaurUseCase dinosaurUseCase = new DinosaurUseCase(dinosaurFactory, dinosaurRepository, dinosaurAssembler, actionRepository, actionFactory);

    static DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
    static DinosaurResource dinosaurResource = new DinosaurResource(dinosaurUseCase, dinosaurDtoAssembler);

    static TurnUseCase turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, dinosaurRepository, actionRepository, zooManager);

    static TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
    static TurnResource turnResource = new TurnResource(turnUseCase, turnDtoAssembler);

    static ResourceFactory resourceFactory = new ResourceFactory();
    static ResourceAssemblers resourceAssemblers = new ResourceAssemblers();
    static ResourceUseCase resourceUseCase = new ResourceUseCase(resourceFactory, resourceRepository, resourceAssemblers, actionRepository, actionFactory);
    static ResourceDtoAssembler resourceDtoAssembler = new ResourceDtoAssembler();
    static ResourceResource resourceResource = new ResourceResource(resourceUseCase, resourceDtoAssembler);

    // Bebe
    static BabyAssembler babyAssembler = new BabyAssembler();
    static BabyUseCase bebeUseCase = new BabyUseCase(dinosaurRepository, babyAssembler, actionRepository, actionFactory, dinosaurFactory);

    static BabyDtoAssembler babyDtoAssembler = new BabyDtoAssembler();
    static BabyResource babyResource = new BabyResource(bebeUseCase, babyDtoAssembler);



    public static ResourceConfig packageConfig = ResourceConfig.forApplication(new Application() {
                                                                     @Override
                                                                     public Set<Object> getSingletons() {
                                                                         Set<Object> resources = new HashSet<>();
                                                                         resources.add(resourceResource);
                                                                         resources.add(turnResource);
                                                                         resources.add(dinosaurResource);
                                                                         resources.add(babyResource);
                                                                         resources.add(new InvalidResourceExceptionMapper());
                                                                         resources.add(new UnknownExceptionGrabber());
                                                                         resources.add(new NotExistentNameExceptionMapper());
                                                                         resources.add(new InvalidGenderExceptionMapper());
                                                                         resources.add(new InvalidSpeciesExceptionMapper());
                                                                         resources.add(new InvalidWeightExceptionMapper());
                                                                         resources.add(new DuplicateNameExceptionMapper());
                                                                         resources.add(new InvalidMotherExceptionMapper());
                                                                         resources.add(new InvalidFatherExceptionMapper());
                                                                         return resources;
                                                                     }

                                                                 }
    );
}
