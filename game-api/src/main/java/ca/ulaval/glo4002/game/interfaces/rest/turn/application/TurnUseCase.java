package ca.ulaval.glo4002.game.interfaces.rest.turn.application;

import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionFactory;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.ActionRepository;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Actions;
import ca.ulaval.glo4002.game.interfaces.rest.actions.entities.Command;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;

import java.util.List;
import java.util.UUID;

public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final TurnAssembler turnAssembler;
    private final ActionRepository actionRepository;
    public TurnUseCase(TurnFactory turnFactory,
                       TurnRepository turnRepository,
                       TurnAssembler turnAssembler,
                       ActionRepository actionRepository) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.turnAssembler = turnAssembler;
        this.actionRepository = actionRepository;
    }

    public void createTurn() {
        setupActions(actionRepository);


        List<Actions> actions = actionRepository.getWaitingActions();
        Turn turn = turnFactory.create(actions);
        actionRepository.execute();
        turnRepository.save(turn);
    }

    public void setupActions(ActionRepository actionRepository) {
//        //Todo add list of 250 kg of water...
//        Actions firstAction = new ActionFactory().create("burger", Command.ADD);
//        Actions secondAction = new ActionFactory().create("salad", Command.RETRIEVE);
//        actionRepository.save(firstAction);
//        actionRepository.save(secondAction);
    }

    public TurnDto getFromId(UUID id) {
        Turn turn = turnRepository.findById(id);

        return turnAssembler.toDto(turn);
    }
}
