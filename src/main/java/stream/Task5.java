package stream;

import stream.utilities.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;

public class Task5 {
    public static Optional<Integer> realization(int... arr) {
        return Arrays
                .stream(arr)
                .distinct()
                .map(Helper::square)
                .map(Helper::findRandomDivider)
                .collect(
                        (Supplier<ArrayList<Integer>>) ArrayList::new,
                        (list, item) -> {
                            var divider = Helper.findRandomDivider(item);
                            list.add(divider);
                            list.add(item / divider);
                        },
                        ArrayList::addAll
                )
                .stream()
                .limit(3)
                .min(Comparator.naturalOrder());
    }
}
