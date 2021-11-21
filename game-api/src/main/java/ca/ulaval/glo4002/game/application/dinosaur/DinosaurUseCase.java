package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange.ChangeWeightDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;

import java.util.List;

public class DinosaurUseCase {
    private final DinosaurFactory dinosaurFactory;
    private final HerdRepository herdRepository;
    private final DinosaurAssembler dinosaurAssembler;
    private final ActionFactory actionFactory;
    private final GameRepository gameRepository;

    public DinosaurUseCase(
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
        Dinosaur dinosaur = dinosaurFactory.create(dto.getName(), dto.getWeight(), dto.getGender(), dto.getSpecies());
        Action addDinosaurAction = actionFactory.createAddDinoAction(dinosaur, herdRepository.findHerd());

        Game game = gameRepository.findGame();
        game.currentTurn().addAction(addDinosaurAction);
        gameRepository.save(game);
    }

    public List<DinosaurDto> getAllDinosaurs() {
        Herd herd = herdRepository.findHerd();
        List<Dinosaur> dinosaurs = herd.findAllDinosaurs();
        return dinosaurAssembler.toDtos(dinosaurs);
    }

    public DinosaurDto getDinosaur(String name) throws NotExistentNameException {
        Herd herd = herdRepository.findHerd();

        Dinosaur dinosaur = herd.findDinosaurByName(name);
        if (dinosaur == null) {
            throw new NotExistentNameException();
        }

        return dinosaurAssembler.toDto(dinosaur);
    }

    public void changeDinosaurWeight(ChangeWeightDto changeWeightDto) {
        //TODO: IMPLEMENTER
    }
}
