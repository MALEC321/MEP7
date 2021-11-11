package ca.ulaval.glo4002.game.domain.resources;

public enum ResourceType {
    BURGER(4),
    SALAD(3),
    WATER(10);

    private final int expiration;

    ResourceType(int expiration) {
        this.expiration = expiration;
    }

    public int getExpiration() {
        return this.expiration;
    }
}
