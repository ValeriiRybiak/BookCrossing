package tasks.task7.repo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum CoffeeRoast {
    CINNAMON("Cinnamon"),
    NEW_ENGLAND("New England"),
    AMERICAN("American"),
    CITY("City"),
    VIENNA("Vienna"),
    ITALIAN("Italian"),
    FRENCH("French");

    private final String roastName;

    public static Optional<CoffeeRoast> getCoffeeRoastTypeFromString(String coffeeRoastType) {
       return Arrays
               .stream(CoffeeRoast.values())
               .filter(v -> v.roastName.equalsIgnoreCase(coffeeRoastType))
               .findFirst();
    }
}
