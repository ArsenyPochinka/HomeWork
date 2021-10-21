package lesson_1.lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {
        int a = -10;
        int b = 6;
        int value = 20;
        printThreeWords();
        checkSumSing(a, b);
        printColor(value);
        compareNumbers(a,b);
    }

    private static void printThreeWords() {
        System.out.println("Orange\n" + "Banana\n" + "Apple");
    }

    private static void checkSumSing(int a, int b) {
        String sign = (a + b) >= 0 ? "The sum of the numbers is positive" : "The sum of the numbers is negative";
        System.out.println(sign);
    }
    private static void printColor(int value) {
        if (value <= 0) {
            System.out.println("Red");
        }
        else if (0 < value && value <= 100) {
            System.out.println("Yellow");
        }
        else System.out.println("Green");
    }
    private static void compareNumbers(int a, int b) {
        String compare = a>=b ? "a>=b" : "a<b";
        System.out.println(compare);
    }
}
