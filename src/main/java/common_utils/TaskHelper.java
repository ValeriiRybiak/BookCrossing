package common_utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TaskHelper {
    public static void printTaskName(String name) {
        System.out.printf("\n--------------------    %s    --------------------%n", name);
    }
}
