package ca.ulaval.glo4002.game.application.dinosaur.dtos;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

import java.util.List;
import java.util.stream.Collectors;

public class DinosaurAssembler {

    public DinosaurDto toDto(Dinosaur dinosaur) {
        return new DinosaurDto(dinosaur.getName(), dinosaur.getWeight(), dinosaur.getGender(),
                dinosaur.getSpecies());
    }

    public List<DinosaurDto> toDtos(List<Dinosaur> dinosaurs) {
        return dinosaurs.stream().map(this::toDto).collect(Collectors.toList());
    }
}
