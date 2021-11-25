package ca.ulaval.glo4002.game.application.resources;

import java.util.List;

import ca.ulaval.glo4002.game.application.resources.dtos.ResourcesDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourceCreationDto;
import ca.ulaval.glo4002.game.controllers.resources.dtos.ResourcesAssembler;
import ca.ulaval.glo4002.game.domain.actions.Action;
import ca.ulaval.glo4002.game.domain.actions.ActionFactory;
import ca.ulaval.glo4002.game.domain.game.Game;
import ca.ulaval.glo4002.game.domain.game.GameRepository;
import ca.ulaval.glo4002.game.domain.resources.Pantry;
import ca.ulaval.glo4002.game.domain.resources.PantryRepository;
import ca.ulaval.glo4002.game.domain.resources.Resources;
import ca.ulaval.glo4002.game.domain.resources.ResourcesGroup;

public class ResourcesService {
    private final ResourcesGroupFactory resourcesGroupFactory;
    private final PantryRepository pantryRepository;
    private final ResourcesAssembler resourcesAssembler;
    private final ActionFactory actionFactory;
    private final GameRepository gameRepository;

    public ResourcesService(ResourcesGroupFactory resourcesGroupFactory, PantryRepository pantryRepository, ResourcesAssembler resourcesAssembler,
                            ActionFactory actionFactory,
                            GameRepository gameRepository) {
        this.resourcesGroupFactory = resourcesGroupFactory;
        this.pantryRepository = pantryRepository;
        this.resourcesAssembler = resourcesAssembler;
        this.actionFactory = actionFactory;
        this.gameRepository = gameRepository;
    }

    public void createResources(ResourceCreationDto resourceCreationDto) {
        ResourcesGroup addResources =
            resourcesGroupFactory.create(resourceCreationDto.getQtyBurger(), resourceCreationDto.getQtySalad(), resourceCreationDto.getQtyWater());
        addResourcesToActionWaitingList(addResources);
        resourcesAssembler.toDto(addResources);
    }

    public List<ResourcesDto> getAllResources() {
        Pantry pantry = pantryRepository.findPantry();
        List<ResourcesGroup> resources = pantry.findAllResourcesGroup();
        return resourcesAssembler.toDtos(resources);
    }

    private void addResourcesToActionWaitingList(ResourcesGroup resourcesGroup) {
        Game game = gameRepository.findGame();
        for (Resources resources : resourcesGroup.getResources().values()) {
            if (!resources.isEmpty()) {
                Action addResources = actionFactory.createAddResourceAction(resources, pantryRepository.findPantry());
                game.currentTurn().addAction(addResources);
            }
        }
        gameRepository.save(game);
    }
}
