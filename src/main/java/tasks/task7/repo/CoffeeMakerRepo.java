package tasks.task7.repo;

import lombok.experimental.UtilityClass;
import tasks.task7.CoffeeMaker;

import java.util.function.Supplier;

import static tasks.task7.repo.CoffeeRoast.AMERICAN;

@UtilityClass
public class CoffeeMakerRepo {
    public static CoffeeMaker<?> getCoffeeMaker1() {
        return CoffeeMaker
                .builder()
                .maxMilkVolume(1.5f)
                .maxWaterVolume(2.5f)
                .milkVolume(1.5f)
                .waterVolume(1.5f)
                .coffeeType(AMERICAN)
                .maxCoffeeBeansVolume(1)
                .coffeeBeansVolume(1)
                .build();
    }
}
