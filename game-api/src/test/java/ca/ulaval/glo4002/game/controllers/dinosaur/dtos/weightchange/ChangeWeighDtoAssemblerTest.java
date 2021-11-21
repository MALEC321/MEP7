package ca.ulaval.glo4002.game.controllers.dinosaur.dtos.weightchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeWeighDtoAssemblerTest {
    private static final int DINO_EXPECTED_WEIGHT = 100;
    private static final String DINO_EXPECTED_NAME = "coco";
    private ChangeWeighDtoAssembler changeWeighDtoAssembler;

    @BeforeEach
    void setUp() {
        changeWeighDtoAssembler = new ChangeWeighDtoAssembler();
    }

    @Test
    void givenChangeWeightRequest_whenConvertingRequest_thenHaveChangeWeightDto() {
        ChangeWeightRequest changeWeightRequest = new ChangeWeightRequest(DINO_EXPECTED_WEIGHT);

        ChangeWeightDto changeWeightDto = changeWeighDtoAssembler.fromRequest(DINO_EXPECTED_NAME, changeWeightRequest);

        assertEquals(DINO_EXPECTED_NAME, changeWeightDto.getDinoName());
        assertEquals(DINO_EXPECTED_WEIGHT, changeWeightDto.getWeightChange());
    }
}