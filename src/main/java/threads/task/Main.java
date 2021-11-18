package threads.task;

import lombok.SneakyThrows;

import java.util.*;

import static common_utils.TaskHelper.getRandomString;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.Collections.synchronizedList;

/**
 * Сделать симуляцию общего чата между двумя потоками. Каждый поток должен передавать сообщения через общую очередь
 * сообщений (выбрать одну из коллекций). Алгоритм работы каждого из потоков выглядит следующим образом:
 * <p>
 * 1. просыпаемся
 * 2. вычитываем полученные сообщения
 * 3. выводим их в консоль
 * 4. пишем свое сообщение
 * 5. если отправлено более 10 сообщений выставляем флаг terminated = true
 * 6. засыпаем на указанное время
 * <p>
 * Главный поток приложения должен следить за состоянием порожденных потоков и завершить выполнение если оба потока
 * завершили свою работу (смотрим Thread.join()). При этом главный поток каждые 0.1 секунды должен выводить состояние
 * порожденных потоков в консоль. (смотрим что такое Thread.State и какие они есть).Создание потоков реализовать
 * разными способами (смотрим способы создания потоков)
 **/
class Client implements Runnable {
    private static volatile Random random = new Random();
    public static volatile List<String> messages = synchronizedList(new ArrayList<>());
    private static volatile String name = "";

    public void setName(String name) {
        Client.name = name;
    }

    private String getThreadName() {
        return currentThread().getName();
    }

    public synchronized void writeRandomMessage() {
        messages.add(getThreadName() + " " + getRandomString());
        System.out.println("                                        write " + getThreadName());
    }

    public void readNewMessages() {
        synchronized (messages) {
            System.out.println(getThreadName() + " unread messages:" + messages.size());
            messages.forEach(System.out::println);
        }

    }

    public void isThreadActive() {
        if (messages.size() >= 10) {
            currentThread().interrupt();
            System.out.println(getThreadName() + " interrupt()");
        }
    }

    public synchronized int getRandomTimeout() {
        return random.nextInt(2 - 1) + 1;
    }


    @SneakyThrows
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            synchronized (messages) {
                if (!name.equals(currentThread().getName())) {
                    readNewMessages();
                    messages.clear();
                }
                setName(currentThread().getName());
                writeRandomMessage();
                Thread.sleep(getRandomTimeout());
                isThreadActive();
            }
        }
    }
}

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        var a = new Thread(new Client(), "a");
        var b = new Thread(new Client(), "b");
        a.start();
        Thread.sleep(5);
        b.start();
        var c = false;
        while (!c) {
            if (a.isInterrupted() || b.isInterrupted()) {
                a.join();
                b.join();
            }
            if (!a.isAlive() && !b.isAlive()) c = true;
            System.out.println("MAIN");
            sleep(100);
        }
        System.out.println("END MAIN");
    }
}