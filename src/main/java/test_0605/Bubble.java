package test_0605;

import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = {10, 3, 11, 5, 9, 7, 1};

        bubbleSort(arr);
    }


    public static void bubbleSort(int[] arr) {
        if (arr == null) {
            return;
        }
        int tmp;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }

        }


        System.out.println(Arrays.toString(arr));

    }
}
