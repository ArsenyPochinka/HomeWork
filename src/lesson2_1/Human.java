package lesson2_1;

public class Human implements RunJump {
    public static final int MAXRUN = 4000;
    public static final int MAXJUMP = 4;
    @Override
    public void run() {
        System.out.println("The human has run");
    }
    @Override
    public void jump() {
        System.out.println("Thr human has jumped");
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
