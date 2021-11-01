package stream.general;

import java.util.List;

import static common_utils.TaskHelper.printTaskName;
import static stream.util.Helper.getRandomStringList;

public class AdditionalMethods {
    public static void main(String[] args) {
        List<String> list = getRandomStringList(100, 5);

        //IsParallel
        printTaskName("IsParallel");

        System.out.printf(
                "parallelStream() -> %b",
                list
                        .parallelStream()
                        .isParallel()
        );
        System.out.printf(
                "\nstream() -> %b",
                list
                        .stream()
                        .isParallel()
        );

        //Parallel
        printTaskName("Parallel");

        list
                .parallelStream()
                .parallel();

        System.out.printf(
                "stream() -> %b",
                list
                        .stream()
                        .parallel()
                        .isParallel()
        );

        //Sequential
        printTaskName("Sequential");

        list
                .stream()
                .sequential();

        System.out.printf(
                "parallelStream() -> %b",
                list
                        .parallelStream()
                        .sequential()
                        .isParallel()
        );
    }
}
