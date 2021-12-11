package stream.general;

import stream.util.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static common_utils.TaskHelper.printTaskName;

public class ConveyorMethods {
    private static Random random = new Random();

    public static void main(String[] args) {
        Supplier<IntStream> intStream = () -> random.ints(0, 100).limit(100);
        Supplier<Stream<String>> stringStream = () -> intStream.get().mapToObj(String::valueOf);
        //Filter
        printTaskName("Filter");

        intStream
                .get()
                .filter(x -> x < 50)
                .forEach(Helper::printNumber);

        //Skip
        printTaskName("Skip");

        intStream
                .get()
                .skip(90)
                .forEach(Helper::printNumber);

        //Distinct
        intStream
                .get()
                .distinct()
                .forEach(Helper::printNumber);

        printTaskName("Distinct");

        //Map
        printTaskName("Map");

        intStream
                .get()
                .map(x -> x % 2)
                .forEach(Helper::printNumber);

        //Peek
        printTaskName("Peek(No changes)");

        intStream
                .get()
                .peek(Math::cos)//nothing
                .forEach(Helper::printNumber);

        printTaskName("Peek(Print first element)");

        intStream
                .get()
                .peek(Helper::printNumber)
                .findFirst();

        //Limit
        printTaskName("Limit");

        intStream
                .get()
                .limit(10)
                .forEach(Helper::printNumber);

        //Sorted
        printTaskName("Sorted(Natural)");

        intStream
                .get()
                .sorted()
                .forEach(Helper::printNumber);

        printTaskName("Sorted(Reverse)");

        intStream
                .get()
                .mapToObj(x -> x)
                .sorted((o1, o2) -> o2.compareTo(o1))
                .forEach(Helper::printNumber);

        //MapToInt
        printTaskName("MapToInt");

        System.out.print(stringStream
                .get()
                .mapToInt(Integer::valueOf)
                .sum()
        );

        //MapToDouble
        printTaskName("MapToDouble");

        stringStream
                .get()
                .mapToDouble(Double::valueOf)
                .forEach(Helper::printNumber);

        //MapToLong
        printTaskName("MapToLong");

        stringStream
                .get()
                .mapToLong(Long::valueOf)
                .forEach(Helper::printNumber);

        //FlatMap
        printTaskName("FlatMap");

        intStream
                .get()
                .flatMap(x -> getInt(x).stream().mapToInt(Integer::valueOf))
                .forEach(Helper::printNumber);
    }

    private static List<Integer> getInt(int number) {
        List<Integer> list = new ArrayList<>();
        while (number >= 1) {
            list.add(number % 10);
            number /= 10;
        }
        return list;
    }
}
