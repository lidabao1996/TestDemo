package test_day517;

import java.util.Arrays;

/**
 * 快排
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arrs = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        System.out.println(arrs.length - 1);
        quickSort(arrs, 0, arrs.length - 1);
        System.out.println(Arrays.toString(arrs));

    }


    public static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int left, right, tmp, bench;

        bench = arr[start];
        left = start;
        right = end;


        //相等的情况下是相遇
        while (left != right) {

            while (arr[right] >= bench && left < right) {
                right--;
            }


            while (arr[left] <= bench && left < right) {
                left++;
            }

            if (left < right) {
                tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }

        }
        //相遇
        arr[start] = arr[left];
        arr[left] = bench;

        quickSort(arr, start, left - 1);//左边部分
        quickSort(arr, left + 1, end);//右边部分

    }
}
