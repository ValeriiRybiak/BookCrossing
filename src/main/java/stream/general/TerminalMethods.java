package stream.general;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static common_utils.TaskHelper.printTaskName;
import static stream.util.Helper.printNumber;

public class TerminalMethods {
    public static void main(String[] args) {
        Supplier<Stream<String>> stringStream = () -> IntStream.range(1, 100).mapToObj(String::valueOf);

        //FindFirst
        printTaskName(" FindFirst ");

        stringStream
                .get()
                .peek(System.out::println)
                .findFirst()
                .get();

        //FindAny
        printTaskName(" FindAny ");

        stringStream
                .get()
                .peek(System.out::println)
                .findAny()
                .get();

        //Count
        printTaskName(" Count ");

        printNumber(
                stringStream
                        .get()
                        .count()
        );

        //AnyMatch
        printTaskName(" AnyMatch ");

        System.out.println(
                stringStream
                        .get()
                        .anyMatch("100"::equals)
        );

        //NoneMatch
        printTaskName(" NoneMatch ");

        System.out.println(
                stringStream
                        .get()
                        .noneMatch("100"::equals)
        );

        //AllMatch
        printTaskName(" AllMatch ");

        System.out.println(
                stringStream
                        .get()
                        .skip(9)
                        .limit(10)
                        .peek(System.out::println)
                        .allMatch("1"::contains)
        );

        //AllMatch
        printTaskName(" AllMatch ");

        System.out.println(
                stringStream
                        .get()
                        .skip(9)
                        .limit(10)
                        .peek(System.out::println)
                        .allMatch("1"::contains)
        );

        //Min
        printTaskName(" Min ");

        System.out.println(
                stringStream
                        .get()
                        .min(String::compareTo)
                        .get()
        );

        //Max
        printTaskName(" Max ");

        System.out.println(
                stringStream
                        .get()
                        .mapToInt(Integer::valueOf)
                        .max()
        );

        //ForEach
        printTaskName(" ForEach ");

        System.out.println("forEach x -> x.concat(\"_\"):");
        stringStream
                .get()
                .peek(System.out::print)
                .forEach(x -> x.concat("_"));

        System.out.println("\nmap x -> x.concat(\"_\"):");
        stringStream
                .get()
//                .peek(System.out::print)
                .map(x -> x.concat("_"))
                .peek(System.out::print)
                .findFirst();

        System.out.println();

        stringStream
                .get()
                .forEach(System.out::print);

        //ForEachOrdered
        printTaskName(" ForEachOrdered ");

        stringStream
                .get()
                .map(x -> x.concat("_"))
                .forEachOrdered(System.out::print);

        //ToArray
        printTaskName(" ToArray ");

        System.out.println(
                Arrays.toString(stringStream
                        .get()
                        .toArray())
        );

        //Reduce
        printTaskName(" Reduce ");

        System.out.println(
                stringStream
                        .get()
                        .mapToInt(Integer::valueOf)
                        .reduce(Integer::sum)
                        .orElseThrow()
        );
    }
}
