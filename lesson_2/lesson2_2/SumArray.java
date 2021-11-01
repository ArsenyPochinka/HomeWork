package lesson_2.lesson2_2;

public class SumArray {

    public static int sumArray(String[][] arr) {
        if (arr[0].length != 4 || arr[1].length != 4 || arr[2].length != 4 || arr[3].length != 4 || arr.length != 4) {
            throw new MyArraySizeException("This array is not 4x4");
        } else {
            int result = 0;
            for (int i=0; i<arr.length; i++) {
                for (int j = 0; j < arr[0].length; j++) {
                    try {
                        result += Integer.parseInt(arr[i][j]);
                    }
                    catch (NumberFormatException e) {
                        throw new MyArrayDataException("This string's value is not parsable integer.\n"+"Value:" + arr[i][j]
                                                       +"\nThe sell of array —> line: " + (i+1) + " column: " + (j+1), e);
                    }
                }
            }
            return result;
        }
    }
}

