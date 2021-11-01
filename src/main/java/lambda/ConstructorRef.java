package lambda;

import lombok.Getter;

interface ABuilder {
    A createA(int a);
}

public class ConstructorRef {
    public static void main(String[] args) {
        ABuilder aBuilder = A::new;
        A a1 = aBuilder.createA(1);
        A a2 = aBuilder.createA(2);
        System.out.println(a1.getA());
        System.out.println(a2.getA());
    }
}

class A {
    @Getter
    private int a;

    public A(int a) {
        this.a = a;
    }
}
