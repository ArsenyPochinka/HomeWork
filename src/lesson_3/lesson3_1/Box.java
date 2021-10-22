package leeson3_1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> boxList;
    private final T fruit;

    public Box(T fruit) {
        boxList = new ArrayList<>();
        this.fruit = fruit;
    }

    public ArrayList<T> getBoxList() {
        return boxList;
    }

    public ArrayList<T> putFruit(int quantityFruit) {
        for (int i = 0; i < quantityFruit ; i++) {
            if(!boxList.isEmpty()) {
                boxList.add(boxList.lastIndexOf(fruit) + 1, fruit);
            }
            else {
                boxList.add(i,fruit);
            }
        }
        return boxList;
    }

    public Float getWeight() {
            return boxList.size() * fruit.getWeight();
    }

    public boolean compare(Box<?> boxFruit) {
        return Math.abs(boxFruit.getWeight()-this.getWeight())<0.00001;
    }

    public void PureIntoABox(Box<T> boxFruit) {
        boxFruit.putFruit(this.getBoxList().size());
        this.getBoxList().clear();
    }
}
