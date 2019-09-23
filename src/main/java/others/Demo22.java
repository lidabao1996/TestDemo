package others;

import java.util.Arrays;

public class Demo22 {
    public static void main(String[] args) {
        int[] arrs = {6, 9, 2, 7, 1, 3, 4, 5, 10, 8};
        selectSort(arrs);
        System.out.println(Arrays.toString(arrs));
    }

    public static void selectSort(int[] arr) {
        int start = 0;
        int minIndex = 0;
        int tmp;
        while (start != arr.length) {
            for (int i = start + 1; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            tmp = arr[start];
            arr[start] = arr[minIndex];


            minIndex = ++start;
        }

    }
}
