package optinal;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Optional;

import static common_utils.TaskHelper.printTaskName;
import static java.util.Optional.*;

public class Actions {
    public static void main(String[] args) {
        Optional<User> o = of(new User("Alex", 20));
        Optional<User> o1 = ofNullable(null);
        /* map */
        printTaskName("map");
        System.out.println(o.map(x -> x.getName().toUpperCase()).toString());
        System.out.println(o1.map(x -> x.getName().toUpperCase()).toString());

        /* filter */
        printTaskName("filter");
        System.out.println(o.filter(x -> x.getAge() > 18).toString());
        System.out.println(o1.filter(x -> x.getAge() > 18).toString());

        /* flatMap */
        printTaskName("flatMap");
//        System.out.println(o.flatMap(x -> x.getName().toUpperCase()));
//        System.out.println(o1.flatMap(x -> x.getName().toUpperCase()));
    }
}

@AllArgsConstructor
@Data
class User{
    private String name;
    private int age;
}
