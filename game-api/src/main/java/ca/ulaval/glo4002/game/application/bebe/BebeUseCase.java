package ca.ulaval.glo4002.game.application.bebe;

import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeAssembler;
import ca.ulaval.glo4002.game.controllers.bebe.dtos.BebeCreationDto;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.actions.ActionRepository;
import ca.ulaval.glo4002.game.domain.bebe.Bebe;
import ca.ulaval.glo4002.game.domain.bebe.BebeFactory;
import ca.ulaval.glo4002.game.domain.bebe.BebeRepository;

public class BebeUseCase {

    private final BebeFactory bebeFactory;
    private final BebeRepository bebeRepository;
    private final BebeAssembler bebeAssembler;
    private final ActionRepository actionRepository;
    private final ActionFactory actionFactory;

    public BebeUseCase(BebeFactory bebeFactory, BebeRepository bebeRepository, BebeAssembler bebeAssembler, ActionRepository actionRepository, ActionFactory actionFactory) {
        this.bebeFactory = bebeFactory;
        this.bebeRepository = bebeRepository;
        this.bebeAssembler = bebeAssembler;
        this.actionRepository = actionRepository;
        this.actionFactory = actionFactory;
    }

    public void createBebe(BebeCreationDto dto) {
        Bebe bebe = bebeFactory.create(dto.name, dto.fatherName, dto.motherName);
    }
}
