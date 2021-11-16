package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;

import java.util.List;

public class DinosaurUseCase {
    private final DinosaurFactory dinosaurFactory;
    private final HerdRepository herdRepository;
    private final DinosaurAssembler dinosaurAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public DinosaurUseCase(
        DinosaurFactory dinosaurFactory,
        HerdRepository herdRepository,
        DinosaurAssembler dinosaurAssembler,
        ActionRepository actionRepository,
        ActionFactory actionFactory) {
        this.dinosaurFactory = dinosaurFactory;
        this.herdRepository = herdRepository;
        this.dinosaurAssembler = dinosaurAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createDinosaur(DinosaurCreationDto dto) {
        Dinosaur dinosaur = dinosaurFactory.create(dto.getName(), dto.getWeight(), dto.getGender(), dto.getSpecies());
        actionRepository.save(actionFactory.create(dinosaur, herdRepository.findHerd()));
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
}
