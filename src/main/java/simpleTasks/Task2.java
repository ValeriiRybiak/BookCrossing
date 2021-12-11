package simpleTasks;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Start of card game
 */
public class Task2 {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int CARDS_PER_PLAYER = 5;
    private static final String[] SUITS = {"clubs", "diamonds", "hearts", "spades"};
    private static final String[] RANK = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king", "ace"};

    public static void main(String[] args) {
        var players = scanner.nextInt();
        System.out.println(Arrays.toString(getCards()));
    }

    private static String[] getCards() {
        var array = new String[SUITS.length * RANK.length];
        for (int i = 0; i < RANK.length; i++) {
            for (int j = 0; j < SUITS.length; j++) {
                array[SUITS.length * i + j] = SUITS[j] + "-" + RANK[i];
            }
        }
        return array;
    }
}
