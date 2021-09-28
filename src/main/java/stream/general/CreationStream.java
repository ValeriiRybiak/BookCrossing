package stream.general;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

public class CreationStream {
    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();
        int[] intArr = new int[0];
        String[] stringArr = new String[0];

        //Classic
        Stream<Integer> classicStream = integerList.stream();

        //From values
        Stream<String> fromValuesStream = Stream.of("A", "B", "C");

        //From array
        IntStream fromArrayStream1 = Arrays.stream(intArr);
        Stream<String> fromArrayStream2 = Arrays.stream(stringArr);

        //From file
        Stream<String> fromFileStream = null;

        try {
            fromFileStream = Files.lines(Paths.get("src/main/resources/test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Objects.requireNonNull(fromFileStream).forEach(out::println);

        //From String
        IntStream fromStringStream = "String".chars();
        fromStringStream.forEach(out::println);

        //Builder
        Stream<Object> builderStream = Stream
                .builder()
                .add(1)
                .add(2)
                .add(3)
                .build();

        //Parallel Stream
        Stream<Integer> parallelStream1 = integerList.parallelStream();
        Stream<Integer> parallelStream2 = integerList.stream().parallel();

        //Iterate
        Stream<Integer> iterateStream = Stream.iterate(0, x -> x+1);

        //Generate
        Stream<Integer> generateStream = Stream.generate(() -> 1);
    }
}
