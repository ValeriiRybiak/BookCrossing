package optinal;

import common_utils.TaskHelper;

import java.util.NoSuchElementException;
import java.util.Optional;

import static common_utils.TaskHelper.printTaskName;

public class GetObject {
    public static void main(String[] args) {
        Optional<Object> o = Optional.of(new Object());
        Optional<Object> o1 = Optional.ofNullable(null);
        /* get */
        printTaskName("get");
        System.out.printf("new Object() -> %s%n", o.get().toString());
        try {
            o.get();
        }catch (NoSuchElementException e){
            System.out.println("null -> NoSuchElementException");
        }

        /* orElse */
        printTaskName("orElse");
        System.out.printf("new Object() -> %s%n", o.orElse("Str"));
        System.out.printf("null -> %s%n", o1.orElse("Str"));

        /* orElseGet */
        printTaskName("orElseGet");
        System.out.printf("new Object() -> %s%n", o.orElse(Integer.MAX_VALUE));
        System.out.printf("null -> %s%n", o1.orElse(Integer.MAX_VALUE));

        /* orElseThrow */
        printTaskName("orElseThrow");
        System.out.printf("new Object() -> %s%n", o.orElseThrow(NullPointerException::new));
        System.out.printf("null -> %s%n", o1.orElseThrow(NullPointerException::new));
    }
}
