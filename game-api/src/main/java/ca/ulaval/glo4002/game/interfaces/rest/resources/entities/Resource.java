package ca.ulaval.glo4002.game.interfaces.rest.resources.entities;

import java.util.ArrayList;
import java.util.List;

public class Resource {
    private final List<ResourceType> resourceTypeList = new ArrayList<>();

    public void addResourceType(ResourceType resourceType) {
        resourceTypeList.add(resourceType);
    }

    public void printAllResources() {
        for (ResourceType resourceType : resourceTypeList) {
            System.out.println("Type : " + resourceType.getResourceName() + "," + " Value: " + resourceType.getResourceQty());
        }
    }
}
