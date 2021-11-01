package lesson_2.lesson2_1;

public class Cat implements RunJump {
    public static final int MAXRUN = 200;
    public static final int MAXJUMP = 20;
    @Override
    public void run() {
        System.out.println("The cat has run");
    }
    @Override
    public void jump() {
        System.out.println("The cat has jumped");
    }
    @Override
    public int getMAXRUN() {
        return MAXRUN;
    }
    @Override
    public int getMAXJUMP() {
        return MAXJUMP;
    }
}
