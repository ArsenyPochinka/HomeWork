public class ArrayMethods {
    private static final int size = 1_000_000;

    public static void firstMethod() {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Total time of the first method: " + (System.currentTimeMillis() - startTime) + " ms.\n");
    }

    public static void secondMethod() throws InterruptedException {
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        float[] leftArr = new float[size / 2];
        float[] rightArr = new float[size / 2];
        System.arraycopy(arr, 0, leftArr, 0, leftArr.length);
        System.arraycopy(arr, leftArr.length, rightArr, 0, rightArr.length);
        long splitTime = System.currentTimeMillis();
        System.out.println("Split array time: " + (splitTime - startTime) + " ms.");

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < leftArr.length; i++) {
                leftArr[i] = (float) (leftArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long fillLeftArrTime = System.currentTimeMillis();
            System.out.println("Time filling the left half of the array: " + (fillLeftArrTime - splitTime) + " ms.");
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < rightArr.length; i++) {
                rightArr[i] = (float) (rightArr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            long fillRightArrTime = System.currentTimeMillis();
            System.out.println("Time filling the right half of the array: " + (fillRightArrTime - splitTime) + " ms.");
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        long fillArrTime = System.currentTimeMillis();
        System.arraycopy(leftArr,0,arr,0,leftArr.length);
        System.arraycopy(rightArr, 0, arr, leftArr.length, rightArr.length);
        System.out.println("Time of merging two arrays: " + (System.currentTimeMillis() - fillArrTime) + " ms.");
        System.out.println("Total time of the second method: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}
