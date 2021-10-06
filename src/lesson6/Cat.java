package lesson6;

public class Cat extends Animals {
    private static int countCats=0;
    public Cat(String name) {
        super(name);
        countCats++;
    }

    @Override
    protected void run(int distance) {
        if(distance<=200) {
            super.run(distance);
        }
        else {
            System.out.println("Сats can't run so much");
        }
    }

    @Override
    protected void swim(int distance) {
        System.out.println("Сats can't swim");
        }

    @Override
    public int getCount() {
        return countCats;
    }
    }

