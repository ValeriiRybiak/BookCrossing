package tasks;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@UtilityClass
public class FindMaxValue {
    public int findMaxValueOfArrayWithStream(int... arr) {
        return Arrays
                .stream(arr)
                .max()
                .orElseThrow();
    }

    public int findMaxValueOfArrayWithCollectionFrame(Integer... arr) {
        return Collections.max(List.of(arr));
    }

    public int findMaxValueOfArrayWithSortedStream(int... arr) {
        return Arrays
                .stream(arr)
                .sorted()
                .toArray()[arr.length];
    }

    public int findMaxValueOfArrayWithMath(int... arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) max = Math.max(max, arr[i]);

        return max;
    }

    public int findMaxValueOfArrayStd(int... arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) max = max > arr[i] ? arr[i] : max;

        return max;
    }
}
