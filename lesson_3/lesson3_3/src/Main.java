import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayTools<Integer> arrayTools = new ArrayTools();
        Integer[] intArr = {1,2,3,4,5,6,7};

        System.out.println(Arrays.toString(intArr));
        System.out.println("Changed places of object whit indexes 0 and 1:\n" + Arrays.toString(arrayTools.getArrayChangedPlacesOfObjects(intArr,0,1)) + "\n");

        List<Integer> list = arrayTools.remakeArrayToList(intArr);
        System.out.println("ArrayList from array:" + list + "\n");

        Box<Apple> appleBox1 = new Box<>(new Apple()); //There is шn the argument, the initialization of the fruit, which is necessary for the operation of the Box
        Box<Apple> appleBox2 = new Box<>(new Apple());
        Box<Orange> orangeBox = new Box<>(new Orange());
        appleBox1.putFruit(5);
        appleBox2.putFruit(15);
        orangeBox.putFruit(10);
        System.out.println("appleBox1's weight =" + appleBox1.getWeight()
                           +"\nappleBox2's weight =" + appleBox2.getWeight()
                           +"\norangeBox's weight =" + orangeBox.getWeight());
        System.out.println("appleBox2==orangeBox? —> " + appleBox2.compare(orangeBox));
        appleBox2.PureIntoABox(appleBox1);
        System.out.println("\nThe appleBox2 pure out into the appleBox1 \nappleBox2 is empty? —> " + appleBox2.getBoxList().isEmpty()
                           + "\nappleBox1 has " + appleBox1.getBoxList().size() + " fruits");





    }

}
