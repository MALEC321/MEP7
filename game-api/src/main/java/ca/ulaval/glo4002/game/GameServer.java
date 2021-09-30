package ca.ulaval.glo4002.game;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.*;
import ca.ulaval.glo4002.game.interfaces.rest.actions.infrastructure.persistence.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.DinosaurResource;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.api.assemblers.DinosaurDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.DinosaurUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurFactory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence.DinosaurRepositoryInMemory;
import ca.ulaval.glo4002.game.interfaces.rest.heartbeat.HeartbeatResource;
import ca.ulaval.glo4002.game.interfaces.rest.exceptions.exceptionMappers.*;
import ca.ulaval.glo4002.game.interfaces.rest.resources.api.ResourceResource;
import ca.ulaval.glo4002.game.interfaces.rest.resources.api.assemblers.ResourceDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.ResourceUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.resources.application.assemblers.ResourceAssemblers;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceFactory;
import ca.ulaval.glo4002.game.interfaces.rest.resources.entities.ResourceRepository;
import ca.ulaval.glo4002.game.interfaces.rest.resources.infrastructure.persistence.ResourceRepositoryInMemory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.TurnResource;
import ca.ulaval.glo4002.game.interfaces.rest.turn.api.assemblers.TurnDtoAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.TurnUseCase;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;
import ca.ulaval.glo4002.game.interfaces.rest.turn.infrastructure.persistence.TurnRepositoryInMemory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class GameServer implements Runnable {
    private static final int PORT = 8181;

    public static void main(String[] args) {
        new GameServer().run();
    }

    public void run() {
        // Turn
        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        ResourceRepository resourceRepository = new ResourceRepositoryInMemory();
        TurnAssembler turnAssembler = new TurnAssembler();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ActionFactory actionFactory = new ActionFactory();
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();


        // Dinosaur
        DinosaurRepository dinosaurRepository = new DinosaurRepositoryInMemory();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);
        DinosaurAssembler dinosaurAssembler = new DinosaurAssembler();
        DinosaurUseCase dinosaurUseCase = new DinosaurUseCase(dinosaurFactory, dinosaurRepository, dinosaurAssembler, actionRepository, actionFactory);

        DinosaurDtoAssembler dinosaurDtoAssembler = new DinosaurDtoAssembler();
        DinosaurResource dinosaurResource = new DinosaurResource(dinosaurUseCase, dinosaurDtoAssembler);

        TurnUseCase turnUseCase = new TurnUseCase(turnFactory, turnRepository, resourceRepository, dinosaurRepository, turnAssembler, actionRepository);

        TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
        TurnResource turnResource = new TurnResource(turnUseCase, turnDtoAssembler);

        ResourceFactory resourceFactory = new ResourceFactory();
        ResourceAssemblers resourceAssemblers = new ResourceAssemblers();
        ResourceUseCase resourceUseCase = new ResourceUseCase(resourceFactory, resourceRepository, resourceAssemblers, actionRepository, actionFactory);
        ResourceDtoAssembler resourceDtoAssembler = new ResourceDtoAssembler();
        ResourceResource resourceResource = new ResourceResource(resourceUseCase, resourceDtoAssembler);

        HeartbeatResource heartbeatResource = new HeartbeatResource();

        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = ResourceConfig.forApplication(new Application() {
                                                                         @Override
                                                                         public Set<Object> getSingletons() {
                                                                             Set<Object> resources = new HashSet<>();
                                                                             resources.add(resourceResource);
                                                                             resources.add(turnResource);
                                                                             resources.add(dinosaurResource);
                                                                             resources.add(heartbeatResource);
                                                                             resources.add(new InvalidResourceExceptionMapper());
                                                                             resources.add(new UnknownExceptionGrabber());
                                                                             resources.add(new NotExistentNameExceptionMapper());
                                                                             resources.add(new InvalidGenderExceptionMapper());
                                                                             resources.add(new InvalidSpeciesExceptionMapper());
                                                                             resources.add(new InvalidWeightExceptionMapper());
                                                                             resources.add(new DuplicateNameExceptionMapper());
                                                                             return resources;
                                                                         }

        }
        );
        ServletContainer container = new ServletContainer(packageConfig);
        ServletHolder servletHolder = new ServletHolder(container);

        contextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (server.isRunning()) {
                server.destroy();
            }
        }
    }
}
