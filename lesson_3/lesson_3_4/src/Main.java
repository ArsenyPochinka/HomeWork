public class Main {
    public static void main(String[] args) {
        Output output = new Output();
        new Thread(()->
        {
            for (int i = 0; i < 5; i++) {
                output.output1();
            }
        }).start();
        new Thread(()->
        {
            for (int i = 0; i < 5; i++) {
                output.output2();
            }
        }).start();
        new Thread(()->
        {
            for (int i = 0; i < 5; i++) {
                output.output3();
            }
        }).start();
    }
}
