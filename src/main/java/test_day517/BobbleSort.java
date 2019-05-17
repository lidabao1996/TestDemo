package test_day517;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BobbleSort {
    public static void main(String[] args) {
        int arr[] = new int[]{3, 6, 4, 2, 11, 10};
        sort(arr);
    }

    public static void sort(int[] arr) {
        int tmp;
        for (int i = 0; i < arr.length - 1; i++) {
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
