package tasks.task_7.pojos;

import lombok.Getter;
import lombok.ToString;
import tasks.task_7.repo.CoffeeRecipes;
import tasks.task_7.repo.CoffeeRoast;

@Getter
@ToString
public class Cup {
    private String coffeeName;
    private CoffeeRoast coffeeRoast;
    private float waterVolume;
    private float milkVolume;
    private float coffeeBeansVolume;

    public Cup(CoffeeRecipes coffeeRecipes) {
        this.coffeeName = coffeeRecipes.getCoffeeName();
        this.coffeeRoast = coffeeRecipes.getCoffeeRoast();
        this.waterVolume = coffeeRecipes.getWaterVolume();
        this.milkVolume = coffeeRecipes.getMilkVolume();
        this.coffeeBeansVolume = coffeeRecipes.getCoffeeBeansVolume();
    }

    public Cup(float waterVolume) {
        this.waterVolume = waterVolume;
    }
}
