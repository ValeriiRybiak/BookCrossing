package codewars;

import java.util.*;
import java.util.stream.*;

/**
    In this kata you have to create all permutations
    of an input string and remove duplicates, if present.
    This means, you have to shuffle all letters
    from the input in all possible orders.
**/
class Permutations {

    public static void main(String[] args) {
        System.out.println(singlePermutations("aboouvz").size());
    }

    public static List<String> singlePermutations(String s) {
        var list = new ArrayList<String>();
        var arr = s.toCharArray();
        if(arr.length != 1){
            var n = IntStream
                    .range(1, arr.length+1)
                    .reduce(1, (x, y) -> x * y);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < arr.length-1; j++) {
                    char temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    list.add(String.valueOf(arr));
                }
            }

            return list
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());
        }
        list.add(s);

        return list;
    }
}
