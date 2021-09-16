package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurFactory;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.assemblers.DinosaurAssembler;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;

import java.util.UUID;

public class DinosaurUseCase {

    private final DinosaurFactory dinosaurFactory;
    private final DinosaurRepository dinosaurRepository;
    private final DinosaurAssembler dinosaurAssembler;
    public DinosaurUseCase(DinosaurFactory dinosaurFactory, DinosaurRepository dinosaurRepository, DinosaurAssembler dinosaurAssembler) {
        this.dinosaurFactory = dinosaurFactory;
        this.dinosaurRepository = dinosaurRepository;
        this.dinosaurAssembler = dinosaurAssembler;
    }

    public void createDinosaur(String name, int weight, String gender, String species) {
        Dinosaur dinosaur = dinosaurFactory.create(name, weight, gender, species);
        dinosaurRepository.save(dinosaur);
    }

//    public TurnDto getFromId(UUID id) {
//        Dinosaur dinosaur = dinosaurFactory.findById(id);
//        return dinosaurAssembler.toDto(dinosaur);
//    }
}
