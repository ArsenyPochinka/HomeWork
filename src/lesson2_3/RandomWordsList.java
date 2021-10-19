package lesson2_3;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomWordsList {
    private static List<String> words = Arrays.asList("apple", "banana", "orange", "mango", "lemon", "cherry", "strawberry");
    private static final Random random = new Random();

    public static String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

}
