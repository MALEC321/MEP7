package ca.ulaval.glo4002.game.application.dinosaur;

import ca.ulaval.glo4002.game.domain.dinosaur.DietStrategy;
import ca.ulaval.glo4002.game.domain.dinosaur.DietStrategyForCarnivore;
import ca.ulaval.glo4002.game.domain.dinosaur.DietStrategyForHerbivore;
import ca.ulaval.glo4002.game.domain.dinosaur.DietStrategyForOmnivore;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType;
import ca.ulaval.glo4002.game.domain.dinosaur.enums.SpeciesDietsCorrespondances;

import static ca.ulaval.glo4002.game.domain.dinosaur.enums.DietType.*;

public class DietStrategyFactory {
    public DietStrategy create(String species) {
        DietType dietType = SpeciesDietsCorrespondances.getDietFromSpecies(species);
        if (dietType == CARNIVORE) {
            return new DietStrategyForCarnivore(dietType);
        } else if (dietType == HERBIVORE) {
            return new DietStrategyForHerbivore(dietType);
        } else {
            return new DietStrategyForOmnivore(dietType);
        }
    }
}
