package lesson_2.lesson2_1;

public class Wall implements Obstacle {
    private static final int MAXHEIGHT = 5;
    @Override
    public boolean overcoming(int height) {
        if(height>=MAXHEIGHT) {
            System.out.println("The wall has been overcome");
            return true;
        }
        else {
            System.out.println("The wall hasn't been overcome");
            return false;
        }
    }
}
