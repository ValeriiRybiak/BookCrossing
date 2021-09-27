package stream;

import org.apache.commons.lang3.ArrayUtils;
import stream.utilities.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Task4 {
    private static int[] realization1(int... arr) {
        var newArr = Arrays
                .stream(arr)
                .filter(i -> i <= 55 && i >= 30)
                .sorted()
                .toArray();
        ArrayUtils.reverse(newArr);

        return newArr;
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
