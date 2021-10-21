package lesson_2.lesson2_3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] arrWords = new String[20];
        for (int i=0; i<arrWords.length; i++) {
            arrWords[i]= RandomWordsList.getRandomWord();
        }
        System.out.println(Arrays.toString(arrWords));
        Map<String, Integer> uniqueWords = new HashMap<>();
        for (String word: arrWords) {
            if(uniqueWords.containsKey(word)) {
                uniqueWords.put(word, uniqueWords.get(word)+1);
            } else {
                uniqueWords.put(word, 1);
            }
        }
        System.out.println(uniqueWords + "\n\n");

        PhoneDirectory directory = new PhoneDirectory();
        directory.createRandomPhoneDirectory(10);
        directory.add("Petrov","+7(999)9876543");
        directory.printPhoneDirectory();
        System.out.println("\n" + directory.get("Petrov"));
    }
}
