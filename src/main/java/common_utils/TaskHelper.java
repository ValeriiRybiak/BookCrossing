package common_utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

import static org.apache.commons.lang3.RandomStringUtils.*;

@UtilityClass
public class TaskHelper {
    public static Random random = new Random();

    public static void printTaskName(String name) {
        System.out.printf("\n--------------------    %s    --------------------%n", name);
    }

    public static String getRandomString() {
        return random(random.nextInt(100));
    }
}
