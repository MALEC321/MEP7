package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import java.util.List;

public class DinosaursResponse {
    private final List<DinosaurResponseItem> items;

    public DinosaursResponse(List<DinosaurResponseItem> items) {
        this.items = items;
    }

    public List<DinosaurResponseItem> getItems() {
        return items;
    }
}
