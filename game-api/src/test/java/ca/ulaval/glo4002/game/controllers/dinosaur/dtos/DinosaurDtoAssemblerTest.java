package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import ca.ulaval.glo4002.game.application.dinosaur.dtos.DinosaurDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DinosaurDtoAssemblerTest {
    private static final String DINO_NAME = "Bob";
    private static final int DINO_WEIGHT = 100;
    private static final String DINO_GENDER = "m";
    private static final String DINO_SPECIES = "Gigantoraptor";
    private static final String DINO_NAME_2 = "Marie";
    private static final int DINO_WEIGHT_2 = 200;
    private static final String DINO_GENDER_2 = "f";
    private static final String DINO_SPECIES_2 = "Gigantoraptor";
    private DinosaurDtoAssembler dinosaurDtoAssembler;
    private DinosaurRequest dinosaurRequest;
    private DinosaurDto dinosaurDto;
    private DinosaurDto dinosaurDto2;
    private List<DinosaurDto> dinosaurDtos;

    @BeforeEach
    void setUp() {
        dinosaurDtoAssembler = new DinosaurDtoAssembler();
        dinosaurRequest = new DinosaurRequest(DINO_NAME, DINO_WEIGHT, DINO_GENDER, DINO_SPECIES);
        dinosaurDto = new DinosaurDto(DINO_NAME, DINO_WEIGHT, DINO_GENDER, DINO_SPECIES);
        dinosaurDto2 = new DinosaurDto(DINO_NAME_2, DINO_WEIGHT_2, DINO_GENDER_2, DINO_SPECIES_2);
        dinosaurDtos = Arrays.asList(dinosaurDto, dinosaurDto2);
    }

    @Test
    void givenCreateDinoRequest_whenConvertingToDinosaurCreationDto_thenThatCreateDinoRequestIsConverted() {
        DinosaurCreationDto actualDto = dinosaurDtoAssembler.fromRequest(dinosaurRequest);

        assertEquals(dinosaurRequest.getName(), actualDto.getName());
        assertEquals(dinosaurRequest.getWeight(), actualDto.getWeight());
        assertEquals(dinosaurRequest.getGender(), actualDto.getGender());
        assertEquals(dinosaurRequest.getSpecies(), actualDto.getSpecies());
    }

    @Test
    void givenADinoDto_whenConvertingToDinoResponse_thenThatDinoDtoIsConverted() {
        DinosaurResponse actualDto = dinosaurDtoAssembler.toResponse(dinosaurDto);

        assertEquals(dinosaurDto.getName(), actualDto.getName());
        assertEquals(dinosaurDto.getWeight(), actualDto.getWeight());
        assertEquals(dinosaurDto.getGender(), actualDto.getGender());
        assertEquals(dinosaurDto.getSpecies(), actualDto.getSpecies());
    }

    @Test
    void givenADinoDtosList_whenConvertingToDinosaursResponse_thenThatDinosDtosAreConverted() {
        DinosaursResponse actualDtos = dinosaurDtoAssembler.toResponse(dinosaurDtos);
        List<DinosaurResponse> actualDinosaursResponse = actualDtos.getItems();

        for (int i = 0; i < actualDinosaursResponse.size(); i++) {
            DinosaurResponse actualDinosaurResponse = actualDinosaursResponse.get(i);
            DinosaurDto expectedDinosaursValues = dinosaurDtos.get(i);
            assertEquals(expectedDinosaursValues.getName(), actualDinosaurResponse.getName());
            assertEquals(expectedDinosaursValues.getWeight(), actualDinosaurResponse.getWeight());
            assertEquals(expectedDinosaursValues.getGender(), actualDinosaurResponse.getGender());
            assertEquals(expectedDinosaursValues.getSpecies(), actualDinosaurResponse.getSpecies());
        }
    }
}