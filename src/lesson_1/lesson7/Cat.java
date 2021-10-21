package ru.geekbrains.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean isHungry;

    public Cat(String name) {
        this.name = name;
        this.appetite = 5;
        this.isHungry = true;
    }

    public void setHungry(boolean hungry) {
        isHungry = hungry;
    }

    public int getAppetite() {
        return appetite;
    }

    public String getName() {
        return name;
    }

    public void info() {
        System.out.println("Name: " + name + ", hungry: " + isHungry);
    }

    public void eat(Plate plate) {
        plate.decreaseFood(appetite);
        isHungry = false;
        System.out.println(name + " поел из тарелки");
    }
}
