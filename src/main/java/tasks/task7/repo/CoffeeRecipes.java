package tasks.task7.repo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static tasks.task7.repo.CoffeeRoast.*;

@RequiredArgsConstructor
@Getter
public enum CoffeeRecipes {
    AMERICANO("Americano", AMERICAN, 0.15f, 0, 0.2f),
    AMERICANO_WITH_MILK("Americano with milk", AMERICAN, 0.15f, 0.5f, 0.2f),
    ESPRESSO("Espresso", NEW_ENGLAND, 0.75f, 0, 0.3f),
    FLAT_WHITE("Flat white", ITALIAN, 0.1f, 0.15f, 0.1f),
    RAFF("Raff", CITY, 0.25f, 0.1f, 0.15f),
    LATTE("Latte", VIENNA, 0.25f, 0.2f, 0.2f);

    private final String coffeeName;
    private final CoffeeRoast coffeeRoast;
    private final float waterVolume;
    private final float milkVolume;
    private final float coffeeBeansVolume;
}
