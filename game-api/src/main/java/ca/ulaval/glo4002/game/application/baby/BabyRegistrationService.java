package ca.ulaval.glo4002.game.application.baby;

import ca.ulaval.glo4002.game.application.dinosaur.DinosaurFactory;
import ca.ulaval.glo4002.game.controllers.baby.dtos.BabyCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.HerdRepository;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.infrastructure.client.dto.ResponseBreed;

import java.util.Optional;

public class BabyRegistrationService {
    private final HerdRepository herdRepository;
    private final ActionFactory actionFactory;
    private final DinosaurFactory dinosaurFactory;
    private final GameRepository gameRepository;

    public BabyRegistrationService(HerdRepository herdRepository, ActionFactory actionFactory,
                                   DinosaurFactory dinosaurFactory, GameRepository gameRepository) {
        this.herdRepository = herdRepository;
        this.actionFactory = actionFactory;
        this.dinosaurFactory = dinosaurFactory;
        this.gameRepository = gameRepository;
    }

    public void saveBabyInformationIfBabyAlived(BabyCreationDto dto, Optional<ResponseBreed> babyDto) {
        if (babyDto.isPresent()) {
            Dinosaur father = herdRepository.findCurrentHerd().findDinosaurByName(dto.getFatherName());
            Dinosaur mother = herdRepository.findCurrentHerd().findDinosaurByName(dto.getMotherName());
            Dinosaur baby = dinosaurFactory.createBabyDinosaur(dto.getName(), mother, father, babyDto.get().getGender(), babyDto.get().getOffspring());

            Game game = gameRepository.findCurrentGame();

            game.currentTurn().addAction(actionFactory.createAddDinoAction(baby, herdRepository.findCurrentHerd()));

            gameRepository.save(game);
        }
    }
}
