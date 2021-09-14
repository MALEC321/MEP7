package ca.ulaval.glo4002.game;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.*;
import ca.ulaval.glo4002.game.interfaces.rest.actions.infrastructure.persistence.ActionRepositoryInMemory;
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
import java.util.UUID;

public class GameServer implements Runnable {
    private static final int PORT = 8181;

    public static void main(String[] args) {
        new GameServer().run();
    }

    public void run() {
        // Turn
        TurnFactory turnFactory = new TurnFactory();
        TurnRepository turnRepository = new TurnRepositoryInMemory();
        TurnAssembler turnAssembler = new TurnAssembler();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        TurnUseCase turnUseCase = new TurnUseCase(turnFactory, turnRepository, turnAssembler, actionRepository);

        TurnDtoAssembler turnDtoAssembler = new TurnDtoAssembler();
        TurnResource turnResource = new TurnResource(turnUseCase, turnDtoAssembler);
        Server server = new Server(PORT);
        ServletContextHandler contextHandler = new ServletContextHandler(server, "/");
        ResourceConfig packageConfig = ResourceConfig.forApplication(new Application() {
            @Override
            public Set<Object> getSingletons() {
                Set<Object> resources = new HashSet<>();
                resources.add(turnResource);
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
