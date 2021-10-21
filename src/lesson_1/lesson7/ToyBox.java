package ru.geekbrains.lesson7;

public class ToyBox {
    private boolean isOpened;
    private Toy toy;

    public ToyBox() {
        isOpened = true;
    }

    public void open() {
        if(!isOpened) {
            isOpened = true;
            System.out.println("Мы открыли коробку");
        } else {
            System.out.println("Коробка уже открыта");
        }
    }

    public void close() {
        if(isOpened) {
            isOpened = false;
            System.out.println("Мы закрыли коробку");
        } else {
            System.out.println("Коробка уже закрыта");
        }
    }

    public void putToy(Toy toy) {
        if(!isOpened) {
            System.out.println("Нельзя положить игрушку в закрытую коробку!");
            return;
        }
        if(this.toy != null) {
            System.out.println("В этой коробке уже лежит игрушка!");
            return;
        }
        this.toy = toy;
        System.out.println("В коробку положили игрушку: " + this.toy.getName());
    }

    public Toy extractToy() {
        if(!isOpened) {
            System.out.println("Нельзя достать игрушку из закрытой коробки!");
            return null;
        }
        if(this.toy == null) {
            System.out.println("В этой коробке ничего не лежит!");
            return null;
        }
        System.out.println("Из коробки достали игрушку: " + this.toy.getName());
        Toy tempToy = this.toy;
        this.toy = null;
        return tempToy;
    }
}
