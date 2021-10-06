package lesson6;

public class Dog extends Animals {
    private static int countDogs=0;
    public Dog(String name) {
        super(name);
        countDogs++;
    }

    @Override
    public void run(int distance) {
        if(distance<=500) {
            super.run(distance);
        }
        else {
            System.out.println("Dogs can't run so much");
        }
    }

    @Override
    public void swim(int distance) {
        if(distance<=10) {
            super.swim(distance);
        }
        else {
            System.out.println("Dogs can't swim so much");
        }
    }

    @Override
    public int getCount() {
        return countDogs;
    }
}

