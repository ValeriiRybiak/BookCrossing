package optinal;

import java.util.Optional;

import static common_utils.TaskHelper.printTaskName;

public class Checks {
    public static void main(String[] args) {
        Optional<Object> o = Optional.of(new Object());
        Optional<Object> o1 = Optional.ofNullable(null);
        /* ifPresent */
        printTaskName("ifPresent");
        o.ifPresent(x -> System.out.printf("new Object() -> %s%n", x.toString()));
        o1.ifPresent(x -> System.out.println(x.toString()));
        /* isPresent */
        printTaskName("isPresent");
        System.out.printf("new Object() -> %s%n", o.isPresent());
        System.out.printf("null -> %s", o1.isPresent());
    }
}
