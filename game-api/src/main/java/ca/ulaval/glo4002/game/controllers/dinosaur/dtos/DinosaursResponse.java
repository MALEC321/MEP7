package ca.ulaval.glo4002.game.controllers.dinosaur.dtos;

import java.util.List;

public class DinosaursResponse {
    private final List<DinosaurResponse> items;

    public DinosaursResponse(List<DinosaurResponse> items) {
        this.items = items;
    }

    public List<DinosaurResponse> getItems() {
        return items;
    }
}
