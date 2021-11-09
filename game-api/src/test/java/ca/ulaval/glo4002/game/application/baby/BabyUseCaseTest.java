package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.baby.breed.Breedable;
import ca.ulaval.glo4002.game.application.baby.dtos.BabyAssembler;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;
import ca.ulaval.glo4002.game.application.exceptions.NotExistentNameException;
import ca.ulaval.glo4002.game.infrastructure.client.BabyBreedableClient;
import ca.ulaval.glo4002.game.infrastructure.persistence.actions.ActionRepositoryInMemory;
import ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur.HerdRepositoryInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

        HerdRepositoryInMemory herdRepositoryInMemory = new HerdRepositoryInMemory();
        BabyAssembler babyAssembler = new BabyAssembler();
        ActionRepository actionRepository = new ActionRepositoryInMemory();
        ActionFactory actionFactory = new ActionFactory();
        SpeciesDietsCorrespondances speciesDietsCorrespondances = new SpeciesDietsCorrespondances();
        DinosaurFactory dinosaurFactory = new DinosaurFactory(herdRepositoryInMemory, speciesDietsCorrespondances);
        Breedable breedable = new BabyBreedableClient();

        babyUseCase = new BabyUseCase(herdRepositoryInMemory, babyAssembler, actionRepository, actionFactory,
                dinosaurFactory, breedable);
    }

    @Test
    public void givenBabyDinosaur_whenGetDinosaurNotExistent_shouldThrowsNotExistentNameException() {
        BabyCreationDto babyCreationDto = new BabyCreationDto(this.name, this.fatherName, this.motherName);
        assertThrows(NotExistentNameException.class, () ->
                babyUseCase.createBaby(babyCreationDto));
    }

}