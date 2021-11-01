package parallel;

import java.util.stream.IntStream;

public class MyThread extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 10).forEachOrdered((x) -> System.out.print(x + " "));
    }
}

class Main{
    public static void main(String[] args) {
        new MyThread().start();
        IntStream.range(10, 20).forEachOrdered((x) -> System.out.print(x + " "));
    }
}
