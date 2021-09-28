package lesson3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HomeWokrApp3 {
    public static void main(String[] args) {

        int[] arr1 = {1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 1};
        System.out.println("#1\nBefore the change:" + Arrays.toString(arr1));
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == 0) arr1[i] = 1;
            else arr1[i] = 0;
        }
        System.out.println("After the change:" + Arrays.toString(arr1));

        int arr2[] = new int[100];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i + 1;
        }
        System.out.println("#2\n" + Arrays.toString(arr2));

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("#3\nBefore the change:" + Arrays.toString(arr3));
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) arr3[i] = arr3[i] * 2;
        }
        System.out.println("After the change:" + Arrays.toString(arr3));

        int[][] arr4 = new int[5][5];
        System.out.println("#4");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j || i == (4 - j)) arr4[i][j] = 1;
                System.out.print(arr4[i][j] + " ");
            }
            System.out.print("\n");
        }

        System.out.println("#5\nfor example n=5, initialValue=5\n" + Arrays.toString(createAnArray(5,5)));

        int[] arr6 = minMaxOfArray(1,2,3,4,-5,-103,201,204);
        System.out.println("#6\nfor example array [1,2,3,4,-5,-103,201,204]\n" + "Minimum of array="+arr6[0]+"\nMaximum of array="+arr6[1]);

        System.out.println("#7\n"
                +"There is a place in the array (for example array [1,1,1,2,1]) where the sum of the left and the right parts of the array are equal — "
                + sumOfLeftAndRightParts(1,1,1,2,1));

        System.out.println("#8\nfor example array [1,2,3,4,5]\n"
                +"for example n=-2: " + Arrays.toString(changeShift_n(-2,1,2,3,4,5))
                +"\nfor example n=2: " + Arrays.toString(changeShift_n(2,1,2,3,4,5)));
    }

    private static int[] createAnArray(int len, int initialValue) {
        int[] arr5 = new int[len];
        for(int i=0; i<len; i++) arr5[i]=initialValue;
        return arr5;
        }

    private static int[] minMaxOfArray(int... arr6) {
        int min = arr6[0];
        int max = arr6[0];
        for(int i=1; i<arr6.length; i++) {
            if (arr6[i] < min) min = arr6[i];
            if (arr6[i] > max) max = arr6[i];
        }
        int[] result = {min, max};
        return result;
        }

    private static boolean sumOfLeftAndRightParts(int... arr7) {
        boolean result=false;
        int sumleft=0;
        for(int i=0; i<arr7.length-1; i++) {
            int sumright=0;
            for(int j=arr7.length-1; j>i; j--) {
                sumright=sumright+arr7[j];
            }
            sumleft=sumleft+arr7[i];
            if(sumright==sumleft) result=true;
        }
        return result;
    }

    private static int[] changeShiftPlus(int... arr8) {
        int variableLast=arr8[arr8.length-1];
        int variable1=arr8[0];
        for(int i=0; i<arr8.length-1; i++) {
            int variable2=arr8[i+1];
            arr8[i+1]=variable1;
            variable1=variable2;
            }
        arr8[0]=variableLast;
        return arr8;
    }

    private static int[] changeShiftMinus(int... arr8) {
        int variableFirst=arr8[0];
        int variable1=arr8[arr8.length-1];
        for(int i=arr8.length-1; i>0; i--) {
            int variable2=arr8[i-1];
            arr8[i-1]=variable1;
            variable1=variable2;
        }
        arr8[arr8.length-1]=variableFirst;
        return arr8;
    }

    private static int[] changeShift_n(int n, int... arr8) {
        if(n>=0) {
            for(int i=0; i<n; i++) arr8 = changeShiftPlus(arr8);
        }
        else {
            for (int i=n; n < 0; n++) arr8 = changeShiftMinus(arr8);
        }
        return arr8;
        }

    }
