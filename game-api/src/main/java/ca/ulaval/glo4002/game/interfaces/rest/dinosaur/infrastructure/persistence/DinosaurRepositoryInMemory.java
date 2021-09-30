package ca.ulaval.glo4002.game.interfaces.rest.dinosaur.infrastructure.persistence;

import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.Dinosaur;
import ca.ulaval.glo4002.game.interfaces.rest.dinosaur.entities.DinosaurRepository;

import java.util.*;
import java.util.stream.Collectors;

public class DinosaurRepositoryInMemory implements DinosaurRepository {
    private final Map<String, Dinosaur> dinosaursByName = new HashMap<>();

    @Override
    public List<Dinosaur> findAll() {
        return new ArrayList<>(dinosaursByName.values());
    }

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

    //TODO: CRÉER TESTS UNITS
    @Override
    public List<Dinosaur> getSortedDinosaursByStrength() {
        return this.findAll().stream()
                .sorted(Comparator.comparingDouble(Dinosaur::getForce))
                .sorted(Comparator.comparing(Dinosaur::getName))
                .collect(Collectors.toList());
    }

}
