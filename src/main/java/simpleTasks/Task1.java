package simpleTasks;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * reverse array
 */
public class Task1 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.toString(array));
        realization3(array);
        System.out.println(Arrays.toString(array));
    }

    private static void realization1(int... array) {
        int temp;
        int arraySize = array.length - 1;
        for (int i = 0; i < arraySize / 2; i++) {
            temp = array[i];
            array[i] = array[arraySize - i];
            array[arraySize - i] = temp;
        }
    }

    private static void realization2(int... array) {
        ArrayUtils.reverse(array);
    }

    private static void realization3(int... array) {
        int temp;
        for (int left = 0, right = array.length - 1; left < right; left++, right--) {
            temp = array[left];
            array[left]  = array[right];
            array[right] = temp;
        }
    }
}
