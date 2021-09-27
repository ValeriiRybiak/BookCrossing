package lambda;

import lombok.Data;
import lombok.NonNull;

import java.util.Locale;
import java.util.Scanner;
import java.util.function.*;

public class ReadyImplementationsLambda {
    public static void main(String[] args) {
        @Data
        class A {
            @NonNull
            int a;

            public A setA(int a) {
                this.a = a;
                return this;
            }
        }
        //Predicate<T> check statement
        Predicate<Integer> isNegative = x -> x < 0;
        System.out.println(isNegative.test(0));
        //BinaryOperator<T> binary operations
        BinaryOperator<Integer> sum = Integer::sum;
        System.out.println(sum.apply(1, 2));
        //UnaryOperator<T> operations with one Object
        UnaryOperator<A> absA = x -> {
            var a = x.getA();
            return x.setA(a * a);
        };

        A a = new A(12);
        System.out.println(absA.apply(a));
        //Function<T,R> transition function from T to R
        Function<Double, Integer> dToI = Double::intValue;
        System.out.println(dToI.apply(1.6));
        //Consumer<T> return void
        Consumer<A> aConsumer = x -> System.out.println(x.toString());
        aConsumer.accept(new A(1));
        //Supplier<T> return T without params
        Supplier<A> aSupplier = () -> {
            Scanner scan = new Scanner(System.in);
            int a1 = scan.nextInt();
            return new A(a1);
        };
        System.out.println(aSupplier.get().toString());
        //

    }
}