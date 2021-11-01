package tasks.task7;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tasks.task7.exceptions.IncorrectRoastType;
import tasks.task7.exceptions.NotEnoughCoffeeBeans;
import tasks.task7.exceptions.NotEnoughMilk;
import tasks.task7.exceptions.NotEnoughWater;
import tasks.task7.pojos.Cup;
import tasks.task7.repo.CoffeeRecipes;
import tasks.task7.repo.CoffeeRoast;

import java.util.NoSuchElementException;
import java.util.Optional;

import static tasks.task7.repo.CoffeeRoast.*;

@Data
@AllArgsConstructor
@Builder
public class CoffeeMaker<T> {
    private static float stdVolume = 0.25f;
    private final float maxWaterVolume;
    private final float maxMilkVolume;
    private float maxCoffeeBeansVolume;
    private float waterVolume;
    private float milkVolume;
    private float coffeeBeansVolume;
    private T coffeeType;
    private int errorCounter;

    public void addWater(float volume) {
        var temp = waterVolume + volume;
        if (temp < maxWaterVolume) waterVolume = maxWaterVolume;
        else waterVolume = temp;
    }

    public void addMilk(float volume) {
        var temp = milkVolume + volume;
        if (temp < maxMilkVolume) milkVolume = maxMilkVolume;
        else milkVolume = temp;
    }

    public void addCoffeeBeans(float volume) {
        var temp = coffeeBeansVolume + volume;
        if (temp < coffeeBeansVolume) coffeeBeansVolume = maxCoffeeBeansVolume;
        else coffeeBeansVolume = temp;
    }

    public Optional<Cup> makeCoffee(CoffeeRecipes coffeeRecipes) throws RuntimeException {
        try {
            checkTypeOfRoast(coffeeRecipes);
            checkVolumes(coffeeRecipes);
            changeVolumes(coffeeRecipes);
            return Optional.of(new Cup(coffeeRecipes));
        } catch (NotEnoughMilk | NotEnoughWater | NotEnoughCoffeeBeans | IncorrectRoastType e) {
            e.printStackTrace();
            errorCounter++;
            return Optional.empty();
        }
    }

    public Optional<Cup> boilWater() {
        if(waterVolume > stdVolume){
            waterVolume -= stdVolume;
            return Optional.of(new Cup(stdVolume));
        }
        if(waterVolume < stdVolume){
            waterVolume = 0;
            return Optional.of(new Cup(waterVolume));
        }
        else return Optional.empty();
    }

    public void changeTypeOfRoast(String newCoffeeRoastTxt) {
        try {
            this.getClass()
                    .getDeclaredField("coffeeType")
                    .set(this, getCoffeeRoastTypeFromString(newCoffeeRoastTxt).get());
            coffeeBeansVolume = 0;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            //log
            System.exit(-1);
        }catch (NoSuchElementException e){
            errorCounter++;
            e.printStackTrace();
        }
    }

    private void checkVolumes(CoffeeRecipes coffeeRecipes) throws NotEnoughWater, NotEnoughMilk, NotEnoughCoffeeBeans {
        if (waterVolume < coffeeRecipes.getWaterVolume()) throw new NotEnoughWater();
        if (milkVolume < coffeeRecipes.getMilkVolume()) throw new NotEnoughMilk();
        if (coffeeBeansVolume < coffeeRecipes.getMilkVolume()) throw new NotEnoughCoffeeBeans();
    }

    private void changeVolumes(CoffeeRecipes coffeeRecipes) {
        waterVolume -= coffeeRecipes.getWaterVolume();
        milkVolume -= coffeeRecipes.getMilkVolume();
        coffeeBeansVolume -= coffeeRecipes.getCoffeeBeansVolume();
    }

    public void checkTypeOfRoast(CoffeeRecipes coffeeRecipes) throws IncorrectRoastType {
        if (coffeeType != coffeeRecipes.getCoffeeRoast()) {
            throw new IncorrectRoastType();
        }
    }
}
