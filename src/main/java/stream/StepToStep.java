package stream;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StepToStep {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Stream.of("a1", "a2", "a3", "b1", "b2", "b3")
                .map(i -> {
                    System.out.println("map ".concat(i));
                    atomicInteger.incrementAndGet();
                    return i.toUpperCase();
                })
                .filter(i -> {
                    System.out.println("filter ".concat(i));
                    return i.contains("3");
                })
                .forEach(System.out::println);
        System.out.println(atomicInteger);
    }
}

