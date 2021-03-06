package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import java.util.List;

public class DinosaurService {
    private final DinosaurFactory dinosaurFactory;
    private final HerdRepository herdRepository;
    private final DinosaurAssembler dinosaurAssembler;
    private final ActionFactory actionFactory;
    private final GameRepository gameRepository;

    public DinosaurService(
        DinosaurFactory dinosaurFactory,
        HerdRepository herdRepository,
        DinosaurAssembler dinosaurAssembler,
        ActionFactory actionFactory,
        GameRepository gameRepository) {
        this.dinosaurFactory = dinosaurFactory;
        this.herdRepository = herdRepository;
        this.dinosaurAssembler = dinosaurAssembler;
        this.actionFactory = actionFactory;
        this.gameRepository = gameRepository;
    }

    public void createDinosaur(DinosaurCreationDto dto) {
        Dinosaur dinosaur = dinosaurFactory.createDinosaur(dto.getName(), dto.getWeight(), dto.getGender(), dto.getSpecies());
        Action addDinosaurAction = actionFactory.createAddDinoAction(dinosaur, herdRepository.findCurrent());

        Game game = gameRepository.findCurrent();
        game.currentTurn().addAction(addDinosaurAction);
        gameRepository.save(game);
    }

    public List<DinosaurDto> getAllDinosaurs() {
        Herd herd = herdRepository.findCurrent();
        List<Dinosaur> dinosaurs = herd.findAllDinosaurs();
        return dinosaurAssembler.toDtos(dinosaurs);
    }

    public DinosaurDto getDinosaur(String name) throws NotExistentNameException {
        Herd herd = herdRepository.findCurrent();

        Dinosaur dinosaur = herd.findDinosaurByName(name);
        if (dinosaur == null) {
            throw new NotExistentNameException();
        }

        return dinosaurAssembler.toDto(dinosaur);
    }
}
