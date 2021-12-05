package threads.task;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;

import static java.lang.String.valueOf;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.util.stream.Collectors.toMap;

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
    private static final Random RANDOM = new Random();
    private static final int MAX_SIZE_OF_MESSAGES_IN_POOL = 3;
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
        return messages
                .entrySet()
                .stream()
                .filter(map -> !map.getKey().equals(getCurrentThreadName()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<String> getMessagesFromCurrentThreads() {
        return messages.get(getCurrentThreadName());
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

    public void isThreadActive() {
        var sizeOfMessagesFromCurrentThread = getMessagesFromCurrentThreads().size();

        if (sizeOfMessagesFromCurrentThread >= MAX_SIZE_OF_MESSAGES_IN_POOL) {
            currentThread().interrupt();
            System.out.println("___________________________________ "
                    .concat(getCurrentThreadName())
                    .concat(" interrupt ")
                    .concat("___________________________________")
            );
        }

    }

    public int getRandomTimeout() {
        return RANDOM.nextInt(2000) + 1;
    }

    private void clearHistory() {
        getMessagesFromOtherThreads().forEach((key, value) -> value.clear());
    }

    @SneakyThrows
    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            sleep(getRandomTimeout());
            synchronized (this) {
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
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("END MAIN");
    }
}