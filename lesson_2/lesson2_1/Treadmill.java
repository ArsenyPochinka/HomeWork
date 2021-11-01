package lesson_2.lesson2_1;

public class Treadmill implements Obstacle {
    private static final int MAXLENGTH = 3000;
    @Override
    public boolean overcoming(int length) {
        if(length>=MAXLENGTH) {
            System.out.println("The treadmill has been overcome");
            return true;
        }
        else {
            System.out.println("The treadmill hasn't been overcome");
            return false;
        }
    }
}
