package lambda.functional_interfaces;

//BiPredicate
@Deprecated
public interface MyPredicate<T> {
    boolean test(T x, T y);
}
