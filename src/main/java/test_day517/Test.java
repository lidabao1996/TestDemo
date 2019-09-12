package test_day517;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {2, 8, 13, 11, 6, 7};
        System.out.println("排序前：" + Arrays.toString(arr));
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("排序后：" + Arrays.toString(arr));

    }


}
