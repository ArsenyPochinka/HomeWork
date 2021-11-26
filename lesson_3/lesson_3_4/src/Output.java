public class Output {
    private static Integer switcher;

    public Output() {
        switcher = 1;
    }

    public synchronized void output1() {
        while (switcher != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        switcher = 2;
        notifyAll();
    }

    public synchronized void output2() {
        while (switcher != 2) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("B");
        switcher = 3;
        notifyAll();
    }

    public synchronized void output3() {
        while (switcher != 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("C");
        switcher = 1;
        notifyAll();
    }
}
