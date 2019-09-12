package others;

import java.util.Arrays;

public class Demo13 {
    public static void main(String[] args) {
        int[] arr = {2, 8, 13, 11, 6, 7};
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
