package ru.geekbrains.lesson7;

import java.util.Arrays;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
//        StringBuilder strBuilder = new StringBuilder(100000);
//        for (int i = 0; i < 100000; i++) {
//            strBuilder.append("A");
//        }
//        System.out.println(strBuilder.toString());

//        String a = "A";
//        String b = "A";
//        System.out.println(a == b);
//        a += "1";
//        b += "1";
//        System.out.println(a == b);

//        String str = "Home Sweet Home";
//        String[] words = str.split(" ");
//        System.out.println(Arrays.toString(words));
//        System.out.println(str);
//
//        System.out.println(str.endsWith("Ho"));

//        Cat cat = new Cat("Barsik");
//        Plate plate = new Plate(50);
//        Plate plate2 = new Plate(50);
//        Plate plate3 = new Plate(50);
//       // plate.info();
//        cat.info();
//
//        cat.eat(plate3);
//
//        plate3.info();
//        cat.info();

        Toy toy = new Toy("Teddy");
        ToyBox toyBox = new ToyBox();
        toyBox.open();
        toyBox.putToy(toy);
        toyBox.close();
        System.out.println(toyBox.extractToy());
        toyBox.open();
        Toy extractedToy = toyBox.extractToy();
        if(extractedToy != null) {
            System.out.println(extractedToy.getName());
        }
    }
}
