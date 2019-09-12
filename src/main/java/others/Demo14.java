package others;

import java.util.Arrays;

public class Demo14 {
    public static void main(String[] args) {
        int[] arr = {2, 8, 13, 11, 6, 7};
        quickSort(0, arr.length - 1, arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int left, int right, int[] arr) {
        int i, j, tmp, temp;
        if (left > right) {
            return;
        }
        tmp = arr[left];
        i = left;
        j = right;


        while (i != j) {
            while (arr[j] >= tmp && i < j) {
                j--;
            }
            while (arr[i] <= tmp && i < j) {
                i++;
            }

            if (i < j) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        arr[left] = arr[i];
        arr[i] = tmp;

        quickSort(left, i - 1, arr);
        quickSort(i + 1, right, arr);
    }
}
