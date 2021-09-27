package lambda;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FunctionalInterface {
    public static void main(String[] args) {
        Operationable<Integer> operationable = (arr) -> (Integer) Arrays
                .stream(arr)
                .mapToInt(Integer::intValue).sum();
        System.out.println(operationable.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}

interface Operationable<T> {
    T sum(T... arr);
}

class Run implements Runnable {
    @Override
    public void run() {
        System.out.println(
                IntStream
                        .range(1, 5)
                        .reduce(Integer::sum)
                        .getAsInt()
        );
    }
}