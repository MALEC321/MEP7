package ca.ulaval.glo4002.game.controllers.resources.dtos;

public class ResourcesResponse {
    private final ResourceResponse fresh;
    private final ResourceResponse expired;
    private final ResourceResponse consumed;

    public ResourcesResponse(ResourceResponse fresh, ResourceResponse expired, ResourceResponse consumed) {
        this.fresh = fresh;
        this.expired = expired;
        this.consumed = consumed;
    }

    public ResourceResponse getFresh() {
        return fresh;
    }

    public ResourceResponse getExpired() {
        return expired;
    }

    public ResourceResponse getConsumed() {
        return consumed;
    }
}
