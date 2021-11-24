package ca.ulaval.glo4002.game.domain.dinosaur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.*;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.BURGER;
import static ca.ulaval.glo4002.game.domain.resources.ResourceType.SALAD;

public class Herd {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    public List<Dinosaur> findAllDinosaurs() {
        return new ArrayList<>(dinosaursByName.values());
    }

    public Dinosaur findDinosaurByName(String name) {
        return dinosaursByName.get(name);
    }

    public void addDinosaur(Dinosaur dinosaur) {
        dinosaursByName.putIfAbsent(dinosaur.getName(), dinosaur);
    }

    public void removeDinosaur(Dinosaur dinosaur) {
        dinosaursByName.remove(dinosaur.getName());
    }

    public List<Dinosaur> findSortedDinosaursByStrengthThenName() {
        List<Dinosaur> copiedDinoList = this.findAllDinosaurs();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).reversed().thenComparing(Dinosaur::getName));

        return copiedDinoList;
    }

//    private List<Dinosaur> findSortedHerbivoreAndOmnivore(final List<Dinosaur> sortedDinosaursByStrengthThenName) {
//        List<Dinosaur> copiedDinoList = new ArrayList<>(sortedDinosaursByStrengthThenName);
//        Collections.reverse(copiedDinoList);
//        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == CARNIVORE);
//
//        return copiedDinoList;
//    }

    public List<Dinosaur> findSortedHerbivoreAndOmnivore() {
        List<Dinosaur> copiedDinoList = this.findAllDinosaurs();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).thenComparing(Dinosaur::getName).reversed());
        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == CARNIVORE);

        return copiedDinoList;
    }

    private List<Dinosaur> findSortedCarnivoreAndOmnivore(List<Dinosaur> sortedDinosaursByStrengthThenName) {
        List<Dinosaur> copiedDinoList = new ArrayList<>(sortedDinosaursByStrengthThenName);
        copiedDinoList.removeIf(dinosaur -> dinosaur.getDiet() == HERBIVORE);

        return copiedDinoList;
    }

    public void removeOrphanedBabyDinosaurs() {
        // Todo make this recursive !
        for (Dinosaur dinosaur : findSortedDinosaursByStrengthThenName()) {
            if (dinosaur.areBothParentsDead()) {
                removeDinosaur(dinosaur);
            }
        }
    }

    public ResourcesStateDto feedDinosaurs(final ResourcesStateDto resourcesStateDto) { // {burger: 10} {salad: 10}  {water: 11}
                                                                                        //{burger: 10} {salad: 0}  {water: 5} GM carnivore
                                                                                        //{burger: 0} {salad: 10}  {water: 5} GM herbivore
        // S1 Split state into states of burger and half water
        // S2 Split state into states of salad and half water
        ResourcesStateDto foodContainerForHerbivore = resourcesStateDto.createFoodContainerForHerbivore();
        ResourcesStateDto foodContainerForCarnivore = resourcesStateDto.createFoodContainerForCarnivore();
        for (Dinosaur dinosaur: findSortedHerbivoreAndOmnivore()) {
            foodContainerForHerbivore = dinosaur.eat(foodContainerForHerbivore); // S2
        }
        for (Dinosaur dinosaur: findSortedCarnivoreAndOmnivore(findSortedDinosaursByStrengthThenName())) {
            foodContainerForCarnivore = dinosaur.eat(foodContainerForCarnivore);
        }

        // S1 U S2
        return foodContainerForHerbivore.add(foodContainerForCarnivore);
    }

    public void removeAllHungryDinosaur() {
        for (Dinosaur dinosaur : findAllDinosaurs()) {
            if (dinosaur.isDead()) {
                dinosaursByName.remove(dinosaur.getName());
            }
        }
    }

    public void clear() {
        dinosaursByName.clear();
    }
}
