package tasks.task7;

import tasks.task7.exceptions.IncorrectRoastType;
import tasks.task7.pojos.Cup;
import tasks.task7.repo.CoffeeRecipes;

import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import static tasks.task7.repo.CoffeeMakerRepo.getCoffeeMaker1;

public class Task7 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IncorrectRoastType {
        CoffeeMaker<?> coffeeMaker = getCoffeeMaker1();
        while (coffeeMaker.getErrorCounter() != 3) {
            System.out.println("Choose option\n\t1. Chose coffee\n\t2. Pour boiling water\n\t3. Add milk\n\t4. Add water\n\t5. Add coffee beans\n\t6. Change roast type");
            Consumer<Optional<Cup>> printCupValue = cup -> cup.ifPresentOrElse(value -> System.out.println(value.toString()), () -> System.out.println("Try again"));
            Optional<Cup> cup;
            switch (scanner.nextInt()) {
                case 1 -> {
                    System.out.println("Choose coffee\n\t1. Americano\n\t2. Americano with milk\n\t3. Espresso\n\t4. Flat white\n\t5. Raff\n\t6. Back");
                    switch (scanner.nextInt()) {
                        case 1 -> {
                            cup = coffeeMaker.makeCoffee(CoffeeRecipes.AMERICANO);
                            printCupValue.accept(cup);
                        }
                        case 2 -> {
                            cup = coffeeMaker.makeCoffee(CoffeeRecipes.AMERICANO_WITH_MILK);
                            printCupValue.accept(cup);
                        }
                        case 3 -> {
                            cup = coffeeMaker.makeCoffee(CoffeeRecipes.ESPRESSO);
                            printCupValue.accept(cup);
                        }
                        case 4 -> {
                            cup = coffeeMaker.makeCoffee(CoffeeRecipes.FLAT_WHITE);
                            printCupValue.accept(cup);
                        }
                        case 5 -> {
                            cup = coffeeMaker.makeCoffee(CoffeeRecipes.RAFF);
                            printCupValue.accept(cup);
                        }
                        default -> System.out.println("Try again");
                    }
                }
                case 2 -> {
                    cup = coffeeMaker.boilWater();
                    printCupValue.accept(cup);
                }
                case 3 -> {
                    System.out.println("Enter water volume");
                    coffeeMaker.addWater(scanner.nextFloat());
                }
                case 4 -> {
                    System.out.println("Enter milk volume");
                    coffeeMaker.addMilk(scanner.nextFloat());
                }
                case 5 -> {
                    System.out.println("Enter coffee beans volume");
                    coffeeMaker.addCoffeeBeans(scanner.nextFloat());
                }
                case 6 -> {
                    System.out.println("Enter new coffee roast type");
                    coffeeMaker.changeTypeOfRoast(scanner.next());
                }
            }
        }
        System.out.println("Please call to support team");
    }
}


