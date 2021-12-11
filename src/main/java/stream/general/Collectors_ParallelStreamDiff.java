package stream.general;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static common_utils.TaskHelper.printTaskName;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toConcurrentMap;
import static stream.util.Helper.getRandomNumber;
import static stream.util.Helper.getRandomStringList;

public class Collectors_ParallelStreamDiff {
    public static void main(String[] args) {
        List<String> list = getRandomStringList(100, 5);

        //ToList/ToSet/ToCollection
        printTaskName("ToList/ToSet/ToCollection");

        list
                .parallelStream()
                .map(x -> x.concat("_"))
                .peek(System.out::print)
                .collect(toCollection(LinkedList::new));

        System.out.println();

        list
                .stream()
                .map(x -> x.concat("_"))
                .peek(System.out::print)
                .collect(toCollection(LinkedList::new));

        //ToConcurrentMap/toMap
        printTaskName("ToConcurrentMap/toMap");

        list
                .parallelStream()
                .peek(System.out::print)
                .collect(toConcurrentMap(
                        k -> k.concat(valueOf(getRandomNumber(5))),
                        String::toUpperCase)
                );

        System.out.println();

        list
                .stream()
                .distinct()
                .peek(System.out::print)
                .collect(toConcurrentMap(
                        k -> k.concat(valueOf(getRandomNumber(5))),
                        v -> v.toUpperCase(Locale.ROOT))
                );
    }
}
