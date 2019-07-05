package test_0605;

import java.util.Arrays;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arrs = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println(arrs.length - 1);
        sort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));

    }


    public static void sort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int bench, left, right, tmp;
        bench = arr[start];
        left = start;
        right = end;

        while (left != right) {
            while (left < right && arr[right] >= bench) {
                right--;
            }

            while (left < right && arr[left] <= bench) {
                left++;
            }


            if (left <  right) {
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }

        }

        //基准归位
        arr[start] = arr[left];
        arr[left] = bench;


        sort(arr, start, left - 1);
        sort(arr, left + 1, end);

    }


}
