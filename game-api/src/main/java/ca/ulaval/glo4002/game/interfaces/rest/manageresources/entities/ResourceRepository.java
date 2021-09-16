package ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities;

import java.util.UUID;

public interface ResourceRepository {
    void add( Resource resource);
    Resource removeStale( UUID resourceId);
}
