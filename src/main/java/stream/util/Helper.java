package stream.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class Helper {
    private final Random RANDOM = new Random();

    public static int getRandomNumber(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static List<String> getRandomStringList(int size, int elementLength) {
        return Stream
                .iterate(1, x -> x + 1)
                .limit(size)
                .map(x -> RandomStringUtils.random(elementLength, true, false))
                .collect(Collectors.toList());
    }

    public int[] getRandomArray(int arrSize, int minValue, int maxValue) {
        int[] arr = new int[arrSize];
        Arrays.setAll(arr, i -> getRandomNumber(minValue, maxValue));
        return arr;
    }

    public void printArray(Integer... arr) {
        System.out.println(
                Arrays
                        .stream(arr)
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "))
        );
    }

    public static int getRandomNumber(int minValue, int maxValue) {
        BiPredicate<Integer, Integer> isCorrectValues = (x, y) -> x > y || x.equals(y);
        if (isCorrectValues.test(minValue, maxValue)) throw new ArithmeticException("Incorrect data");

        return RANDOM
                .ints(minValue, maxValue + 1)
                .findFirst()
                .orElseThrow();
    }

    public static Integer[] boxed(int... arr) {
        return Arrays
                .stream(arr)
                .boxed()
                .toArray(Integer[]::new);
    }

    public static int findRandomDivider(int number) {
        int steps = 1;
        if (number <= 100 && number >= 10)
            steps = getRandomNumber(1, 3);
        else if (number > 100)
            steps = getRandomNumber(1, 5);

        return findDivider(number, steps);
    }

    public static int findDivider(int number, int steps) {
        int currentPositiveStep = 0;
        int temp = 1;
        for (int i = number - 1; i > 1; i--) {
            if (number % i == 0) {
                currentPositiveStep++;
                temp = i;
                if (currentPositiveStep == steps) return i;
            }
        }
        return temp;
    }

    public static int square(int a) {
        return (int) Math.pow(a, 2);
    }


    public static void printNumber(Number x) {
        System.out.print(String.valueOf(x).concat(" "));
    }
}