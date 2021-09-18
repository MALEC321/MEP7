package ca.ulaval.glo4002.game.interfaces.rest.manageresources.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities.Resource;
import ca.ulaval.glo4002.game.interfaces.rest.manageresources.entities.ResourceRepository;

import java.util.*;

public class ResourceRepositoryInMemory implements ResourceRepository {
    private Queue<Resource> inventory = new LinkedList<>();

    @Override
    public void add(Resource resource) {
        inventory.add(resource);
    }

    @Override
    public void removeStale() {
        inventory.remove();
    }

    public int getTotalElementInventory(){
        return inventory.size();
    }

    public void printAllData() {
        for(Resource resource : inventory){
            resource.printAllResources();
        }
    }
}