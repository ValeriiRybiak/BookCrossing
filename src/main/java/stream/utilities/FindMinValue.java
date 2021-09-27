package stream.utilities;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class FindMinValue {
    public int findMinValueOfArrayWithStream(int... arr) {
        return Arrays
                .stream(arr)
                .min()
                .orElseThrow();
    }

    public int findMinValueOfArrayWithCollectionFrame(Integer... arr) {
        return Collections.min(List.of(arr));
    }

    public int findMinValueOfArrayWithSortedStream(int... arr) {
        return Arrays
                .stream(arr)
                .sorted()
                .toArray()[0];
    }

    public int findMinValueOfArrayWithMath(int... arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) min = Math.min(min, arr[i]);

        return min;
    }

    public int findMinValueOfArrayStd(int... arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) min = min > arr[i] ? arr[i] : min;

        return min;
    }
}