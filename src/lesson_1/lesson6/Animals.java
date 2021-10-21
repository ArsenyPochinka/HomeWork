package lesson_1.lesson6;

public abstract class Animals {
    protected String name;
    private static int countAnimals=0;

    public Animals(String name) {
        this.name = name;
        countAnimals++;
    }

    protected void run(int distance) {
        System.out.println(name + " ran " + distance + " meters");
    }

    protected void swim(int distance) {
        System.out.println(name + " swam " + distance + " meters");
    }

    public int getCount() {
        return countAnimals;
    }
}
