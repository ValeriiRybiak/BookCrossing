package tasks;

import stream.util.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Task4 {
    private static int[] realization1(int... arr) {
        return Arrays
                .stream(arr)
                .filter(i -> i <= 55 && i >= 30)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::valueOf)
                .toArray();
    }

    private static Integer[] realization2(int[] arr) {
        var array = Helper.boxed(arr);
        var resList = new ArrayList<Integer>(Arrays.asList(array));
        resList.removeIf(i -> i >= 55 || i <= 30);
        Collections.reverse(resList);

        return resList.toArray(Integer[]::new);
    }

    private static Integer[] realization3(int[] arr) {
        var arrSize = arr.length;
        var resList = new ArrayList<Integer>();
        for (int i = 0; i < arrSize; i++)
            if (i <= 55 && i >= 30) resList.add(i);
        resList.sort(Comparator.reverseOrder());

        return resList.toArray(Integer[]::new);
    }
}
