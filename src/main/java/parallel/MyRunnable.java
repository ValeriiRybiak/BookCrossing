package parallel;

import java.util.stream.IntStream;

public class MyRunnable implements  Runnable{
    @Override
    public void run() {
        IntStream.range(0, 10).forEachOrdered((x) -> System.out.print(x + " "));
    }
}

class Main2{
    public static void main(String[] args) {
        new Thread(new MyRunnable()).start();
        IntStream.range(10, 20).forEachOrdered((x) -> System.out.print(x + " "));
    }
}
