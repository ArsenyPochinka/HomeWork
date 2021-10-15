package lesson2_2;

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
                        System.out.println("Value:" + arr[i][j]);
                        System.out.printf("The sell of array —> line: %d, column: %d \n", i+1, j+1);
                        throw new MyArrayDataException("This string's value is not parsable integer.", e);
                    }
                }
            }
            return result;
        }
    }
}

