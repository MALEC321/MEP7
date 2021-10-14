package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;

public class DinosaurAssembler {

    public DinosaurDto toDto(Dinosaur dinosaur) {
        DinosaurDto dto = new DinosaurDto();
        dto.name = dinosaur.getName();
        dto.weight = dinosaur.getWeight();
        dto.gender = dinosaur.getGender();
        dto.species = dinosaur.getSpecies();
        return dto;
    }

    public List<DinosaurDto> toDtos(List<Dinosaur> dinosaurs) {
        return dinosaurs.stream().map(this::toDto).collect(Collectors.toList());
    }
}
