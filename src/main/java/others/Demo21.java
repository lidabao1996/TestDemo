package others;

import java.util.Arrays;

public class Demo21 {
    public static void main(String[] args) {
        int[] arrs = {6, 9, 2, 7, 1, 3, 4, 5, 10, 8};
        selectionSort(arrs);
        System.out.println(Arrays.toString(arrs));

    }

    public static void selectionSort(int[] arr) {
        int start = 0, tmp, minIndex = 0;
        while (start != arr.length) {
            for (int i = start+1; i < arr.length; i++) {
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            tmp = arr[start];
            arr[start] = arr[minIndex];
            arr[minIndex] = tmp;


            minIndex=++start;
        }
    }
}
