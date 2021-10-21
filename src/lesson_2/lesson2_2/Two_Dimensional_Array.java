package lesson_2.lesson2_2;

import java.util.Arrays;
import java.util.Random;

public class Two_Dimensional_Array {
    public static String[][] makeAndDisplayArray(int value) throws MyArraySizeException {
        Random random = new Random();
        String elements = "0123456789a";
        String[][] arr = new String[value][value];
        for(String[] a: arr) {
            for(int j=0; j<a.length; j++) {
                a[j] = String.valueOf(elements.charAt(random.nextInt(elements.length())));
            }
            System.out.println(Arrays.toString(a));
        }
        return arr;
    }
}
