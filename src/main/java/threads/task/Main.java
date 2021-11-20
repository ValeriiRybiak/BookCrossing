package threads.task;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

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
    private static final Random random = new Random();
    public static Map<String, List<String>> messages = new ConcurrentHashMap<>();
    private static int counter = 0;

    private String getCurrentThreadName() {
        return currentThread().getName();
    }

    public void writeRandomMessage() {
        try {
            messages.get(getCurrentThreadName()).add(valueOf(++counter));
        } catch (NullPointerException e) {
            messages.put(getCurrentThreadName(), new ArrayList<>());
            messages.get(getCurrentThreadName()).add(valueOf(counter));
        }
        System.out.println("                                        write " + getCurrentThreadName() + " value: " + counter);
    }

    private Map<String, List<String>> getMessagesFromOtherThreads() {
        Supplier<Map<String, List<String>>> messages = () -> Client.messages
                .entrySet()
                .stream()
                .filter(map -> !map.getKey().equals(getCurrentThreadName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return messages.get();
    }

    public void readNewMessages() {
        System.out.println(getCurrentThreadName() + " unread messages: " + getMessagesFromOtherThreads().toString());
        printMessagesNewMessagesFromOtherThreads();
        clearHistory();
    }

    private void printMessagesNewMessagesFromOtherThreads() {
        getMessagesFromOtherThreads()
                .forEach((key, value) -> System.out.println("From: " + key + " message: " +
                        value
                                .stream()
                                .collect(Collector.of(
                                        StringBuilder::new,
                                        (sb, message) -> sb.append(message).append(", "),
                                        StringBuilder::append,
                                        sb -> {
                                            var messages = sb.toString();
                                            if (messages.endsWith(", "))
                                                return messages.substring(0, messages.length() - 2);
                                            return messages;
                                        }
                                ))
                ));
    }

    private void printMessagesOfInterruptedThreads() {
        System.out.println("From: " + getCurrentThreadName() + " message: " + messages
                .get(getCurrentThreadName())
                .stream()
                .collect(Collector.of(
                        StringBuilder::new,
                        (sb, message) -> sb.append(message).append(", "),
                        StringBuilder::append,
                        sb -> {
                            var messages = sb.toString();
                            if (messages.endsWith(", "))
                                return messages.substring(0, messages.length() - 2);
                            return messages;
                        }
                )) + " interrupt ______________________________"
        );
    }

    public void isThreadActive() {
        var isOneOfThreadWroteMoreThan3Messages = getMessagesFromOtherThreads()
                .values()
                .stream()
                .anyMatch(x -> x.size() >= 3);
        var isCurrentThreadWroteMoreThan3Messages = messages.get(getCurrentThreadName()).size() >= 3;
        if (isOneOfThreadWroteMoreThan3Messages || isCurrentThreadWroteMoreThan3Messages) {
            printMessagesOfInterruptedThreads();
            clearOwnHistory();
            currentThread().interrupt();
        }
    }

    public int getRandomTimeout() {
        return random.nextInt(2000) + 1;
    }

    private void clearHistory() {
        getMessagesFromOtherThreads().forEach((key, value) -> value.clear());
    }

    private void clearOwnHistory() {
        messages.remove(getCurrentThreadName());
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            sleep(getRandomTimeout());
            synchronized (random) {
                if (!messages.isEmpty()) readNewMessages();
                writeRandomMessage();
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
        var c = new Thread(new Client(), "c");
        a.start();
        b.start();
        c.start();
        var isAllThreadsAreInterrupted = false;
        while (!isAllThreadsAreInterrupted) {
            synchronized (new Object()) {
                if (!a.isAlive() && a.isInterrupted()) a.join();
                if (!b.isAlive() && b.isInterrupted()) b.join();
                if (!c.isAlive() && c.isInterrupted()) c.join();
                if (!a.isAlive() && !b.isAlive() && !c.isAlive()) isAllThreadsAreInterrupted = true;
                System.out.println("__________________MAIN__________________");
                System.out.println("a State -> " + a.getState());
                System.out.println("b State -> " + b.getState());
                System.out.println("c State -> " + c.getState());
                System.out.println("__________________MAIN__________________");
            }
            sleep(1000);
        }
        System.out.println("END MAIN");
    }
}