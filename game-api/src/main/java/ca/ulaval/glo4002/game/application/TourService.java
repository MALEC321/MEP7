package ca.ulaval.glo4002.game.application;

import ca.ulaval.glo4002.game.domain.Tour;
import ca.ulaval.glo4002.game.domain.TurnRepository;

import java.util.ArrayList;
import java.util.List;

public class TourService implements TurnRepository{
    private final List<Integer> turnNumber = new ArrayList<>();
    private final Tour tour;

    public TourService(Tour tour) {
        this.tour = tour;
    }

    @Override
    public int turn() {
        int sum = 0;
        if (tour.turn() > 0){
            turnNumber.add(1);
            sum++;
        }
        System.out.println("sum is "+sum);
        System.out.println("size is "+turnNumber.size());
        return sum == turnNumber.size() ? turnNumber.size() : sum;
    }

    @Override
    public void reset() {
       turnNumber.clear();
    }
}
