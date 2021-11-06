package ca.ulaval.glo4002.game.application.dinosaur;

import java.util.List;

import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurAssembler;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurCreationDto;
import ca.ulaval.glo4002.game.controllers.dinosaur.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Herd;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;

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

        this.herdRepository.add(new Herd());
    }

    public void createDinosaur(DinosaurCreationDto dto) {
        Dinosaur dinosaur = dinosaurFactory.create(dto.name, dto.weight, dto.gender, dto.species);
        actionRepository.save(actionFactory.create(dinosaur, herdRepository));
    }

    public List<DinosaurDto> getAllDinosaurs() {
        Herd herd = herdRepository.findHerd();
        List<Dinosaur> dinosaurs = herd.findAll();
        return dinosaurAssembler.toDtos(dinosaurs);
    }

    public DinosaurDto getDinosaur(String name) throws NotExistentNameException {
        Herd herd = herdRepository.findHerd();
        if (herd.findByName(name) == null) {
            throw new NotExistentNameException();
        }
        Dinosaur dinosaur = herd.findByName(name);
        return dinosaurAssembler.toDto(dinosaur);
    }
}
