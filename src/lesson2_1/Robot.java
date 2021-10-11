package lesson2_1;

public class Robot implements RunJump {
    public static final int MAXRUN = 10000;
    public static final int MAXJUMP = 10;
    @Override
    public void run() {
        System.out.println("The robot has run");
    }
    @Override
    public void jump() {
        System.out.println("The robot has jumped");
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
