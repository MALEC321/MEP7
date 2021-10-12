package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.exceptions.types.NotExistentNameException;
import ca.ulaval.glo4002.game.repositories.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.repositories.dinosaur.DinosaurRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BabyUseCaseTest {

    private String name;
    private String fatherName;
    private String motherName;
    private String gender;
    private String species;
    private BabyUseCase babyUseCase;

    @BeforeEach
    void setUp() {
        this.name = "";
        this.fatherName = " Arthur Turing";
        this.motherName = "Girolamo Cardano";
        this.gender = "f";
        this.species = "Ankylosaurus";

        DinosaurRepository  dinosaurRepository = new DinosaurRepositoryInMemory();
        BabyAssembler babyAssembler = new BabyAssembler();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ActionFactory actionFactory = new ActionFactory();
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(dinosaurRepository, speciesDietsCorrespondances);

        babyUseCase = new BabyUseCase(dinosaurRepository, babyAssembler, actionRepository, actionFactory, dinosaurFactory);
    }

    @Test
    public void babyDinosaur_whenGetDinosaurNotExistent_shouldThrowsNotExistentNameException(){
        BabyCreationDto babyCreationDto = new BabyCreationDto();
        babyCreationDto.name = this.name;
        babyCreationDto.fatherName = this.fatherName;
        babyCreationDto.motherName = this.motherName;
        babyCreationDto.gender = this.gender;
        babyCreationDto.species = this.species;

        babyUseCase.createBebe(babyCreationDto);

        assertThrows(NotExistentNameException.class, () ->
                babyUseCase.getParentsSpecies(fatherName, motherName));

    }

}