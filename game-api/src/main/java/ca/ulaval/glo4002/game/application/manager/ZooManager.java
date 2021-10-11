package ca.ulaval.glo4002.game.application.manager;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.manager.Eatable;
import ca.ulaval.glo4002.game.domain.manager.Feedable;
import ca.ulaval.glo4002.game.domain.resources.ResourceRepository;



public class ZooManager implements Eatable, Feedable {

    private final ResourceRepository resourceRepository;

    public ZooManager(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public boolean eat() {
        return false;
    }

    @Override
    public boolean feed(Dinosaur dinosaur) {
        boolean dead = false;

        if (dinosaur.getDiet().equals(DietType.HERBIVORE)) {
            if (!resourceRepository.removeSalads(dinosaur.feedFood())) {
                dead = true;
            }
        } else {
            if (!resourceRepository.removeBurgers(dinosaur.feedFood())) {
                dead = true;
            }
        }
        if (!resourceRepository.removeWater(dinosaur.feedWater())) {
            dead = true;
        }
        return dead;
    }
}
