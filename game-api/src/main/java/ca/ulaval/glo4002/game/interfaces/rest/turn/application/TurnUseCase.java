package ca.ulaval.glo4002.game.interfaces.rest.turn.application;

import ca.ulaval.glo4002.game.interfaces.rest.turn.application.assemblers.TurnAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnCreationDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.application.dtos.TurnDto;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.Turn;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnFactory;
import ca.ulaval.glo4002.game.interfaces.rest.turn.entities.TurnRepository;

import java.util.UUID;

public class TurnUseCase {

    private final TurnFactory turnFactory;
    private final TurnRepository turnRepository;
    private final TurnAssembler turnAssembler;
    public TurnUseCase(TurnFactory turnFactory,
                       TurnRepository turnRepository,
                       TurnAssembler turnAssembler) {
        this.turnFactory = turnFactory;
        this.turnRepository = turnRepository;
        this.turnAssembler = turnAssembler;
    }

    public void createTurn(TurnCreationDto dto) {
        Turn turn = turnFactory.create(dto.actions);
        turnRepository.save(turn);
    }

//    public TurnDto getFromId(UUID id) {
//        Turn turn = turnRepository.findById(id);
//
//        return turnAssembler.toDto(turn);
//    }
}
