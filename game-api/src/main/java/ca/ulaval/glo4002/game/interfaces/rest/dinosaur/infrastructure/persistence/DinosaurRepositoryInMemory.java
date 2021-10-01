package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import java.util.*;
import java.util.stream.Collectors;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    @Override
    public List<Dinosaur> findAll() { return new ArrayList<>(dinosaursByName.values()); }

    @Override
    public Dinosaur findByName(String name) {
        return dinosaursByName.get(name);
    }

    @Override
    public void save(Dinosaur dinosaur) {
        dinosaursByName.put(dinosaur.getName(), dinosaur);
    }

    @Override
    public void remove(Dinosaur dinosaur) {
        dinosaursByName.remove(dinosaur.getName());
    }

    @Override
    public List<Dinosaur> getSortedDinosaursByStrengthThenName() {
        List<Dinosaur> copiedDinoList = this.findAll();

        copiedDinoList.sort(Comparator.comparing(Dinosaur::getStrength).reversed().thenComparing(Dinosaur::getName));

        return copiedDinoList;
    }

    @Override
    public void reset(){
        dinosaursByName.clear();
    }

}
