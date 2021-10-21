package lesson3_1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTools<T> {

    public T[] getArrayChangedPlacesOfObjects(T[] arr,int ind1, int ind2) {
        T value1 = arr[ind1];
        T value2 = arr[ind2];
        arr[ind1] = value2;
        arr[ind2] = value1;
        return arr;
    }

    public ArrayList<T> remakeArrayToList(T[] arr) {
        List<T> list = new ArrayList<>();
        for(T obj: arr) {
            list.add(obj);
        }
        return (ArrayList<T>) list;
        }
}
