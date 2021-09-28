package stream.tasks;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class FindAverageValue {
    public int findAverageValueOfArrayWithStream(int... arr) {
        return (int) Arrays
                .stream(arr)
                .average()
                .orElseThrow();
    }

    public int findAverageValueOfArrayWithSortedStream(int... arr) {
        return Arrays
                .stream(arr)
                .sorted()
                .toArray()[arr.length / 2];
    }

    public int findAverageValueOfArrayStd(int... arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) sum += arr[i];

        return sum / arr.length;
    }
}
