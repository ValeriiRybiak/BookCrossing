package optinal;

import java.util.NoSuchElementException;
import java.util.Optional;

public class CreateOptionalObject {
    public static void main(String[] args) {
        Object o = new Object();
        Object o1 = null;
        /* Optional.of */
        System.out.printf("Optional.of new Object() -> %s%n", Optional.of(o));
        try {
            Optional.of(o1);
        } catch (NullPointerException e) {

            System.out.println("Optional.of null -> NullPointerException");
        }
        /* Optional.ofNullable */
        System.out.printf("Optional.ofNullable new Object() -> %s%n", Optional.ofNullable(o));
        System.out.printf("Optional.ofNullable null -> %s%n", Optional.ofNullable(o1));
        /* Optional.empty */
        System.out.printf("Optional.empty -> %s", Optional.empty());
    }
}
