package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.assemblers;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.application.dtos.DinosaurDto;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;

import java.util.List;
import java.util.stream.Collectors;

public class DinosaurAssembler {

    public DinosaurDto toDto(Dinosaur dinosaur) {
        DinosaurDto dto = new DinosaurDto();

        return dto;
    }

    public List<DinosaurDto> toDtos(List<Dinosaur> dinosaurs) {
        return dinosaurs.stream().map(this::toDto).collect(Collectors.toList());
    }
}
