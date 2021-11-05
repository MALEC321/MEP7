package ca.ulaval.glo4002.game.infrastructure.persistence.dinosaur;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.ulaval.glo4002.game.domain.dinosaur.Dinosaur;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurBaby;
import ca.ulaval.glo4002.game.domain.dinosaur.DinosaurRepository;

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
        dinosaursByName.putIfAbsent(dinosaur.getName(), dinosaur);
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
    public boolean areBothParentsDead(Dinosaur dinosaur) {
        return dinosaursByName.get(((DinosaurBaby) dinosaur).getFatherName()) == null
            && dinosaursByName.get(((DinosaurBaby) dinosaur).getMotherName()) == null;
    }

    @Override
    public void reset() {
        dinosaursByName.clear();
    }

}
