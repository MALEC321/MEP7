package ca.ulaval.glo4002.game.controllers.resources.dtos;

public class ResourcesResponse {
    private ResourceResponse fresh;
    private ResourceResponse expired;
    private ResourceResponse consumed;

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
