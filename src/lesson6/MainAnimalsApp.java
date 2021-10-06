package lesson6;

public class MainAnimalsApp {
    public static void main(String[] args) {
        Animals[] animals = {new Dog("Ball"), new Dog("Cheese"),new Cat("Vasya"), new Cat("Murzic"), new Cat("Fast")};
        for(Animals i: animals) {
            i.run(600);
            i.run(100);
            i.swim(20);
            i.swim(5);
        }
        System.out.println("Number of animals: " + (((Cat) animals[2]).getCount() + ((Dog) animals[0]).getCount())
                          +"\nNumber of cats: " + ((Cat) animals[2]).getCount()
                          +"\nNumber of dogs: " + ((Dog) animals[0]).getCount());
    }
}
